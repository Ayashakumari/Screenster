package com.screenster.sosts.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.screenster.sosts.Activity.MainActivity;
import com.screenster.sosts.Adapter.PopupAdapter;
import com.screenster.sosts.R;
import com.screenster.sosts.Util.GPSTracker;
import com.screenster.sosts.Util.SaveSharedPrefernces;
import com.screenster.sosts.Util.helper;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import static android.app.Activity.RESULT_CANCELED;
import static android.content.ContentValues.TAG;
import static com.screenster.sosts.Util.helper.location;


//import com.google.android.gms.contextmanager.Relation;

public class MapView extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback {

    TextView toolbar_txt, select_service;
    ImageView FAB;
    Location copied_locaton;
    String post_id, other_user_id;
   // Might be null if Google Play services APK is not available.
    TextView locationSearch;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    String st,str1,helper_location,str,features;

    Geocoder geocoder;
    RelativeLayout street_view_layout;
    List<Address> addresses_list;
    Timer mTimer, mTimer1;
//    ArrayList<Map_Model> map_list = new ArrayList<>();
    Animation zoomOutAnimation;
    String msg = "";
    Activity act;
    LatLng coordinate;
    ArrayList<String> list;
    int i = 0;
    SaveSharedPrefernces ssp;
    MainActivity mainActivity = new MainActivity();
   ImageView check;
    Boolean bool = true;
    GoogleMap googleMap;
    public static final int RequestPermissionCode = 1;
    Marker marker;
    String api_lattitude = "", api_longitude = "";
    Double latitude = null, longitude = null;
    GPSTracker gpsTracker;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleMap mMap;
    int flag = 0;
    public static RelativeLayout search_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_view,
                container, false);


        helper.intent_location = 0;
        ssp = new SaveSharedPrefernces();
        gpsTracker = new GPSTracker(getActivity());
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        act = getActivity();
       // act_data=getActivity();
        api_lattitude = String.valueOf(latitude);
        api_longitude = String.valueOf(longitude);


        locationSearch = (TextView) rootView.findViewById(R.id.locationSearch);



       search_layout = (RelativeLayout) rootView.findViewById(R.id.search_layout);


        helper.freg_status = 1;

        // Create the LocationRequest object

        setUpMapIfNeeded();


        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        search_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //create_edittxt6.setText("Please select date");
                try {
                    Intent intent =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .build(getActivity());
                    startActivityForResult(intent, 1);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }


            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mGoogleApiClient.isConnected()) {

           // LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }


    private void setUpMapIfNeeded() {

        Log.d("if needed","if needed");
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.


            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_frag);
            mapFrag.getMapAsync(this);

//            StreetViewPanoramaFragment streetViewPanorama =
//                    (StreetViewPanorama)getFragmentManager().findFragmentById(R.id.map_frag);
//            streetViewPanorama.getStreetViewPanoramaAsync(this);


            // Check if we were successful in obtaining the map.
            if (mMap != null) {

                marker = mMap.addMarker(new MarkerOptions()
                        .position(coordinate)
                        .title("Start")
                        .snippet("Your Position")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 20));


                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .position(new LatLng(latitude, longitude))
                        .title("Point A")
                        .snippet("Bus Stop")
                        .flat(true));

                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .position(new LatLng(latitude, longitude))
                        .title("Point B")
                        .snippet("Bus Stop")
                        .flat(true));


                marker.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));



                setUpMap();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        int RESULT_OK = -1;

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                Log.d("lat", String.valueOf(place.getLatLng()));
                locationSearch.setText(place.getAddress());
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                List<Address> addressList = null;
                location = locationSearch.getText().toString();


                if (location != null && !location.equals("")) {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                        Address address = addressList.get(0);
                        Log.d("addressList", String.valueOf(address));
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(address.getAdminArea()));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                        locationSearch.setText(marker.getTitle());
                       // helper.location= String.valueOf(location_search.getText().toString());
                        Log.d("location","<<><><><"+ location);

                        MarkerOptions options = new MarkerOptions()
                                .position(latLng)
                                .title("I am here!");
                        mMap.addMarker(options);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                        mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater(), getActivity()));
                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {

                                Log.d("cactivity result","cactivity result");

                                String str2 = marker.getTitle();
                                locationSearch.setText(str2);







                                helper.latitude=String.valueOf(latitude);
                                helper.longitude=String.valueOf(longitude);
                                location=String.valueOf(locationSearch.getText().toString());

                                Log.d("location","<><><><><"+ location);
                                int locat = getActivity().getIntent().getIntExtra("getlocation", 0);
                                Log.d("getlocation","<><>"+locat);
                                //Map.this.startActivity(new Intent(Map.this, SignPostDetails.class));


//                                if (locat == 1) {
//                                    helper.intent_location = 1;
//
//                                    Intent i = new Intent(Map.this, MainActivity.class);
//                                    i.putExtra("neighbourhood", str2);
//                                    i.putExtra("lat",helper.latitude);
//                                    i.putExtra("lang",helper.longitude);
//                                    startActivity(i);
//                                }
////
////
////
//                                else if (locat == 2) {
//
//                                    helper.intent_location = 2;
//
//                                    Intent i = new Intent(Map.this, MainActivity.class);
//                                    i.putExtra("neighbourhood", str2);
//                                    i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                                    startActivity(i);
//                                                      finish();
//                                } else if (locat == 3) {
//
//                                    helper.intent_location = 3;
//                                    Intent i = new Intent(Map.this, MainActivity.class);
//                                    i.putExtra("post street", str2);
//                                    startActivity(i);
//                                }


                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    public void onMapSearch(View view) {
        Log.d("mapserach","map search");

        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null && !location.equals("")) {
            Geocoder geocoder = new Geocoder(getActivity());
            try {
                addressList = geocoder.getFromLocationName(location, 1);
                final Address address = addressList.get(0);



                Log.d("searchlocation", String.valueOf(address));
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(address.getAdminArea()));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));



                locationSearch.setText(String.valueOf(marker.getTitle()));
                location= String.valueOf(locationSearch.getText().toString());
                Log.d("location","<<><><><"+ location);
                mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater(), getActivity()));
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String str2 = marker.getTitle();
                        Log.d("cmapserach","cmap search");
                        helper_location=String.valueOf(str2);
                        //Log.d("location","<><><><><"+ location);






                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getActivity(), "Enter Location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("map ready","map ready");
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                mMap.clear();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                String cityName = null, locatity = null;
                Geocoder gcd = new Geocoder(getActivity().getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(latLng.latitude
                            ,
                            latLng.longitude, 1);
                    Log.d("list1", addresses.toString());
                    if (addresses.size() > 0)
                        System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getSubAdminArea();
                    locatity = addresses.get(0).getLocality();
                    helper.latitude=String.valueOf(addresses.get(0).getLatitude());
                    helper.longitude=String.valueOf(addresses.get(0).getLongitude());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(cityName)
                        .snippet(locatity);
                mMap.addMarker(options);
                locationSearch.setText(marker.getTitle());
               // helper.location= String.valueOf(location_search.getText().toString());
                Log.d("location","<<><><><"+ location);

                mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater(), getContext()));
                marker.showInfoWindow();
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Log.d("cmap ready","cmap ready");
                        String str2 = marker.getTitle();

                        helper_location=str2;

                        location=String.valueOf(str2);
                        Log.d("location","<><><><><"+ location);



                        int locat = getActivity().getIntent().getIntExtra("getlocation", 0);
                        Log.d("getlocation","<><>"+locat);
                       // Map.this.startActivity(new Intent(Map.this, SignPostDetails.class));


//                       if (locat == 1) {
//                            helper.intent_location = 1;
//
//                            Intent i = new Intent(Map.this, MainActivity.class);
//                            i.putExtra("neighbourhood", str2);
//                           i.putExtra("lat",helper.latitude);
//                           i.putExtra("lang",helper.longitude);
//                            startActivity(i);
//                        }
////
////
////
//                        else if (locat == 2) {
//
//                            helper.intent_location = 2;
//
//                           Intent i = new Intent(Map.this, MainActivity.class);
//                           i.putExtra("neighbourhood", str2);
//                           i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                           startActivity(i);
//                        } else if (locat == 3) {
//
//                            helper.intent_location = 3;
//                            Intent i = new Intent(Map.this, MainActivity.class);
//                            i.putExtra("post street", str2);
//                           i.putExtra("lat",helper.latitude);
//                           i.putExtra("lang",helper.longitude);
//
//                           Log.d("lat",""+helper.latitude);
//                           Log.d("lang",""+helper.longitude);
//                            startActivity(i);
//                        }


                    }
                });



                locationSearch.setText(String.valueOf(options.getTitle()));


                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));

                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        str = marker.getTitle();
                        Log.d("addresses", str);
                        locationSearch.setText(str);


                       getActivity().finish();
                    }
                });
                Log.d("address", cityName);

            }
        });
        setUpMap();
    }


    private void setUpMap() {

        Log.d("setup map","setup map");
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker"));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng point) {
                mMap.clear();
                if (marker != null) {
                    marker.remove();
                }
                String s = null;
                String cityName = "";
                String address = null;
                String postalcode = "";
                String sublocality = "";
                String addmin = "";
                final MarkerOptions markerOptions = new MarkerOptions();

                Geocoder gcd = new Geocoder(getActivity().getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(point.latitude,
                            point.longitude, 1);
                    Log.d("list2", addresses.toString());
                    if (addresses.size() > 0)
                        System.out.println(addresses.get(0).getLocality());
                    address = addresses.get(0).getAddressLine(1) + "," + addresses.get(0).getAddressLine(2) + "," + addresses.get(0).getAddressLine(3);
                    Log.d("address", address);
                    cityName = addresses.get(0).getAddressLine(0);
                    addmin = addresses.get(0).getAddressLine(2);
                    postalcode = addresses.get(0).getAddressLine(3);
                    sublocality = addresses.get(0).getLocality();

                    helper.latitude=String.valueOf(addresses.get(0).getLatitude());

                    helper.longitude=String.valueOf(addresses.get(0).getLongitude());

                    if (addmin == null) {
                        addmin = "";
                    }
                    if (postalcode == null) {
                        postalcode = "";
                    }
                    if (sublocality == null) {
                        sublocality = "";
                    }
                    s = sublocality + " "

                            + addmin + " " + postalcode;
                    Log.d("markerplace", s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //  mMap.addMarker(new MarkerOptions().position(point).title(s).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                markerOptions.position(point);
                // markerOptions.position(adapter.getItem(0).getLatLng());
                markerOptions.snippet(sublocality);
                markerOptions.title(cityName);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                marker = mMap.addMarker(markerOptions);
                mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater(), getContext()));
                marker.showInfoWindow();



                locationSearch.setText(String.valueOf(markerOptions.getTitle()));
                location= String.valueOf(locationSearch.getText().toString());
                Log.d("location","<<><><><"+ location);



//                ssp.setKEY_Eventlocaton(Map.this, marker.getTitle());
//                ssp.setKEY_Eventlatitude(Map.this, String.valueOf(point.latitude));
//                ssp.setKEY_Eventlongitude(Map.this, String.valueOf(point.latitude));
//                ssp.setKEY_CityName(Map.this, marker.getSnippet());
                //  mMap.setInfoWindowAdapter(new PopupAdapterMarker(getLayoutInflater()));
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        Log.d("csetup map","csetup map");
                        str = marker.getTitle();
                        helper_location=str;






//
//                        Map.this.startActivity(new Intent(Map.this, SignPostDetails.class));
//                        int locat = Map.this.getIntent().getIntExtra("getlocation", 0);
//                        Log.d("getlocation","<><>"+locat);
//
//                        if (locat == 1) {
//                            helper.intent_location = 1;
//
//                            Intent i = new Intent(Map.this, MainActivity.class);
//                            i.putExtra("neighbourhood", str);
//                            i.putExtra("lat",helper.latitude);
//                            i.putExtra("lang",helper.longitude);
//                            startActivity(i);
//                        }
////
////
////
//                        else if (locat == 2) {
//
//                            helper.intent_location = 2;
//
//                            Intent i = new Intent(Map.this, MainActivity.class);
//                            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                            startActivity(i);
//                        } else if (locat == 3) {
//
//                            helper.intent_location = 3;
//                            Intent i = new Intent(Map.this, MainActivity.class);
//                            i.putExtra("post street", str);
//                            i.putExtra("lat",helper.latitude);
//                            i.putExtra("lang",helper.longitude);
//
//                            Log.d("lat",""+helper.latitude);
//                            Log.d("lang",""+helper.longitude);
//                            startActivity(i);
//                        }


                        Log.d("addresses", str);


                    }
                });

            }
        });
    }

    private void handleNewLocation(Location location) {

        Log.d("handle new location","new Location");
        mMap.clear();
        Log.d(TAG, location.toString());
        String s = null;
        String cityName = "";
        String address = null;
        String postalcode = "";
        String sublocality = "";
        String addmin = "";
        final double currentLatitude;
        final double currentLongitude;
        if (latitude == null) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
        } else {
            currentLatitude = latitude;
            currentLongitude = longitude;


        }

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
        Geocoder gcd = new Geocoder(getActivity().getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(currentLatitude,
                    currentLongitude, 1);
            Log.d("list3", addresses.toString());
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
            }
            cityName = addresses.get(0).getAddressLine(0);
            addmin = addresses.get(0).getAddressLine(2);
            postalcode = addresses.get(0).getAddressLine(3);
            sublocality = addresses.get(0).getAddressLine(1);
            helper.latitude=String.valueOf(addresses.get(0).getLatitude());
            helper.longitude=String.valueOf(addresses.get(0).getLongitude());


           /* if (addmin == null) {
                addmin = "";
            }
            if (postalcode == null) {
                postalcode = "";
            }
            if (sublocality == null) {
                sublocality = "";
            }*/
            s = cityName;



            Log.d("markerplace", s);
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(cityName);

            marker = mMap.addMarker(options);
            mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater(), getActivity()));
            marker.showInfoWindow();
            locationSearch.setText(String.valueOf(marker.getTitle()));
           // location= String.valueOf(locationSearch.getText().toString());
            Log.d("location","<<><><><"+ location);
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    str1 = marker.getTitle();

                    helper_location=str1;
//                    Log.d("chandle new location","cnew Location");
//
//
//                        Map.this.startActivity(new Intent(Map.this, SignPostDetails.class));
//                        int locat = Map.this.getIntent().getIntExtra("getlocation", 0);
//
//                    Log.d("getlocation","<><>"+locat);
//
//
//                    if (locat == 1) {
//                        helper.intent_location = 1;
//
//                        Intent i = new Intent(Map.this, MainActivity.class);
//                        i.putExtra("neighbourhood", str1);
//                        i.putExtra("lat",helper.latitude);
//                        i.putExtra("lang",helper.longitude);
//                        startActivity(i);
//                    }
////
////
////
//                    else if (locat == 2) {
//
//                        helper.intent_location = 2;
//
//                        Intent i = new Intent(Map.this, MainActivity.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(i);
//                    } else if (locat == 3) {
//
//                        helper.intent_location = 3;
//                        Intent i = new Intent(Map.this, MainActivity.class);
//                        i.putExtra("post street", str1);
//                        i.putExtra("lat",helper.latitude);
//                        i.putExtra("lang",helper.longitude);
//
//                        Log.d("lat",""+helper.latitude);
//                        Log.d("lang",""+helper.longitude);
//
//
//                        startActivity(i);
//                    }



                }


            });
            locationSearch.setText(str1);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        } else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()) {
            try {

                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);

            } catch (IntentSender.SendIntentException e) {

                e.printStackTrace();
            }
        } else {

            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(getActivity(), "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, RequestPermissionCode);

        }
    }


}