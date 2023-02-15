package com.ushatech.porter.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.LocationRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.anyGranted
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ushatech.porter.R
import com.ushatech.porter.databinding.FragmentHomeBinding
import com.ushatech.porter.presentation.BaseFragment
import com.ushatech.porter.ui.activity.CourierEstimateActivity
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : BaseFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, View.OnTouchListener  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentHomeBinding


    private lateinit var mMap: GoogleMap
    var testLocation = LatLng(22.7525323,75.8918181)
    //Adding the fusedLocationClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var currentLocation: LatLng? = null
    private lateinit var supportMapFragment: SupportMapFragment
    private val AUTOCOMPLETE_REQUEST_CODE = 1

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setupGoogleMaps()
        checkPermissions()
        setupAutoCompletePlaces()
        initClicks()

        return binding.root

    }

    private fun setupAutoCompletePlaces() {
        Places.initialize(fragmentContext, getString(R.string.GOOGLE_API_KEY), Locale.US);
    }

    private fun setupCameraChangeListener(){
        if(mMap!=null){
            mMap.setOnCameraChangeListener(object : GoogleMap.OnCameraChangeListener {
                override fun onCameraChange(cameraPosition: CameraPosition) {
                    var latLangCurrentMap = mMap.cameraPosition.target
                    showLog("${cameraPosition.target}")
                    showSnackBar(binding.root,"${cameraPosition.target}")

                }
            })

        }



    }

    private fun setupGoogleMaps() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        supportMapFragment =
            (childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?)!!
        supportMapFragment.getMapAsync(this)
    }

    private fun initClicks() {

        binding.toolbarMaps.btnChangePickLocation.setOnClickListener {
            // Set the fields to specify which types of place data to
            // return after the user has made a selection.
            val fields = listOf(Place.Field.ID, Place.Field.NAME)
            // Start the autocomplete intent.
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .setTypesFilter(listOf(TypeFilter.CITIES.toString()))
                .setCountries(listOf("IN"))
                .build(fragmentContext)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)

        }
        binding.bottomBarMaps.btnAllIndiaParcel.setOnClickListener {
            val intentCourierEstimate = Intent(fragmentContext,CourierEstimateActivity::class.java)
            startActivity(intentCourierEstimate)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        Log.i("DEV_ASHUTOSH-->", "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Log.i("DEV_ASHUTOSH-->", status.statusMessage ?: "")
                    }
                }
                Activity.RESULT_CANCELED -> {
                    showLog("No location picked by user from Google Auto Suggestions.")
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = intializeMapData(p0)
        getLastKnownLocation()
        setupCameraChangeListener()
    }
    fun checkPermissions() {
        permissionsBuilder(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ).build().send { result ->
            if (result.anyGranted()) {
                getLastKnownLocation()

            }else{
                showToast("Please allow location permission to enable this feature.")

            }
        }

    }

    @SuppressLint("MissingPermission")
    fun intializeMapData(mMap: GoogleMap): GoogleMap {
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.isTrafficEnabled = true
        //Disabling the map toolbar to get the full interface in the native app only.
        mMap.uiSettings.isMapToolbarEnabled = false
        permissionsBuilder(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ).build().send {
            if (it.allGranted()) {
                mMap.isMyLocationEnabled = true
            } else {
                showToast("Please allow all location permissions")

            }

        }




        return mMap

    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() {
        //check for the permission here. !
        // THE FUSED LOCATION CLIENT OF THE GOOGLE PLAY SERVICES API ARE BETTER
        // THAN THE android.location (in-built) services.
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {

                    currentLocation = LatLng(location.latitude,location.longitude)

                    var gps = LatLng(location.latitude, location.longitude)
                    mMap.addMarker(MarkerOptions().position(gps).title("You are here !"))
                    val cameraPosition = CameraPosition.Builder()
                        .target(gps) // Sets the center of the map to location user
                        .zoom(15f) // Sets the zoom

                        .build() // Creates a CameraPosition from the builder
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                    // use your location object
                    // get latitude , longitude and other info from this
                    // AFTER GETTING THE CURRENT LOCATION MAKE THE RETROFIT REQUEST. FOR PLACES PREDICTIONS.
                }

            }
    }
    override fun onMarkerClick(p0: Marker): Boolean {
        showToast("Marker is clicked")
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}