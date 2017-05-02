package com.example.dell.forecast;

import android.content.Intent;;
import android.databinding.tool.util.L;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    MapView mapView;
    TextView latView, lngView, ArrayX,ArrayY;
    GoogleMap googleMap;
    Polygon largePolygon, clickPolygon;
    Polyline line1, line2;
    private Double arrayX, arrayY;
    private int pointX,pointY;
    private String fileNameX,fileNameY;



    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        latView =(TextView) rootView.findViewById(R.id.lat_view);
        lngView =(TextView) rootView.findViewById(R.id.lng_view);
        ArrayX =(TextView) rootView.findViewById(R.id.array_x);
        ArrayY =(TextView) rootView.findViewById(R.id.array_y);

        mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // needed to get the map to display immediately


        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(this);

        Button chartVeiewBtn = (Button) rootView.findViewById(R.id.ChartViewButton);
        chartVeiewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChartViewActivity.class);
                intent.putExtra("ValueX",fileNameX);
                intent.putExtra("ValueY",fileNameY);
                startActivity(intent);



            }
        });



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
////        if (context instanceof OnFragmentInteractionListener) {
////            mListener = (OnFragmentInteractionListener) context;
////        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnFragmentInteractionListener");
////        }

//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onMapReady(GoogleMap mMap) {

        final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");
        googleMap = mMap;
        LatLng bangkok = new LatLng(11, 99);

//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            googleMap.setMyLocationEnabled(true);
//        } else {
//            // Show rationale and request permission.
//        }


       largePolygon = googleMap.addPolygon(new PolygonOptions() // Large polygon
                .add(new LatLng(15, 95),
                        new LatLng(15, 105.08),
                        new LatLng(4.92, 105.08),
                        new LatLng(4.92, 95))
                .strokeColor(Color.RED).strokeWidth(1)
                .fillColor(Color.parseColor("#30000000")));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                Log.e("latlong", latLng.longitude + "-" + latLng.latitude);

                latView.setText("Lat : "+REAL_FORMATTER.format(latLng.latitude));
                lngView.setText("Lng : "+REAL_FORMATTER.format(latLng.longitude));



                if((latLng.longitude>=95 && latLng.latitude<=15)&&(latLng.longitude<=105.08 && latLng.latitude>=4.92))
                {
                    if(latLng.longitude > 105.0793 && latLng.longitude <=105.08)
                    {
                        arrayX = 105.0793-95;
                    }else
                    {
                        arrayX = latLng.longitude-95;
                    }


                    arrayY = 15-latLng.latitude;
                    arrayX =(arrayX/0.0833);
                    arrayY =(arrayY/0.0833);
                    Log.i("array",arrayX + "-" +arrayY);

                    pointX = arrayX.intValue();
                    pointY = arrayY.intValue();
                    Log.i("point",pointX + "-" +pointY);

                    clickPolygon = googleMap.addPolygon(new PolygonOptions()
                            .add(new LatLng(15 - (0.0833 * pointY), 95 + (0.0833 * pointX)),
                                    new LatLng(15 - (0.0833 * pointY), 95 + (0.0833 * pointX) + 0.0833),
                                    new LatLng(15 - (0.0833 * pointY) - 0.0833, 95 + (0.0833 * pointX) + 0.0833),
                                    new LatLng(15 - (0.0833 * pointY) - 0.0833, 95 + (0.0833 * pointX)))
                            .strokeColor(Color.RED).strokeWidth(1)
                            .fillColor(Color.parseColor("#C62828")));



                    pointX = pointX+1;
                    pointY = pointY+1;
                    fileNameX = String.valueOf(pointX);
                    fileNameY = String.valueOf(pointY);
                    ArrayX.setText("X : "+fileNameX);
                    ArrayY.setText("Y : "+fileNameY);

                    if(fileNameX.length()==1)  // make point file name for send to sever .php
                    {
                        fileNameX = "00"+fileNameX;
                    }
                    else if (fileNameX.length()==2)
                    {
                        fileNameX = "0"+fileNameX;
                    }


                    if (fileNameY.length()==1)
                    {
                        fileNameY = "00"+fileNameY;
                    }
                    else if (fileNameY.length()==2)
                    {
                        fileNameY = "0"+fileNameY;
                    }

                    Log.i("name",""+fileNameX+"- " + fileNameY);

                }
                else
                {
                    fileNameX="121";//ดิน
                    fileNameY="001";
                    ArrayX.setText("X : -");
                    ArrayY.setText("Y : -");
                }

            }
        });




        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // For zooming automatically to the location of the marker
        googleMap.addMarker(new MarkerOptions().position(bangkok).title("Thailand"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(bangkok).zoom(5).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

}
