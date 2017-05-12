package com.example.dell.forecast;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.dell.forecast.databinding.FragmentNewsBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context context;
    ArrayList<NewsListModel> dataList = new ArrayList<NewsListModel>();
    FragmentNewsBinding fragmentNewsBinding;
    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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

        fragmentNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        View rootView = fragmentNewsBinding.getRoot();
        initInstance();

        return rootView;
    }

    public void initInstance()
    {

        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        fragmentNewsBinding.recyclerview.setHasFixedSize(true);
        fragmentNewsBinding.recyclerview.setAdapter(new NewsRecycleViewAdapter(dataList,context));
        fragmentNewsBinding.recyclerview.setLayoutManager(MyLayoutManager);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        dataList.add(new NewsListModel("เตือน27-29เม.ย.มีพายุฤดูร้อน ระวังอันตราย'ลูกเห็บ-ฟ้าผ่า'","เมื่อวันที่ 27 เม.ย. กรมอุตุนิยมพยากรณ์อากาศ 24 ชั่วโมงข้างหน้าว่า ประเทศไทยตอนบนมีอากาศร้อนและมีอากาศร้อนจัดบางพื้นที่ โดยมีฝนฟ้าคะนองบางแห่งในภาคเหนือ ภาคตะวันออกเฉียงเหนือ ภาคตะวันออก และภาคใต้ ในช่วงวันที่ 27-29 เม.ย. 60 ประเทศไทยตอนบนจะมีพายุฤดูร้อน โดยมีฝนฟ้าคะนองและลมกระโชกแรงเกิดขึ้นในหลายพื้นที่ กับมีลูกเห็บตกและฟ้าผ่าได้บางแห่ง ขอให้ประชาชนในบริเวณดังกล่าวระมัดระวังอันตรายจากสภาวะอากาศที่จะเกิดขึ้นไว้ด้วย \n" +
                "\n" +
                "ลักษณะสำคัญทางอุตุนิยมวิทยา    ความกดอากาศต่ำเนื่องจากความร้อนปกคลุมประเทศไทยตอนบน ประกอบกับลมตะวันตกและลมตะวันตกเฉียงใต้พัดปกคลุมภาคตะวันออกเฉียงเหนือ ภาคตะวันออก และภาคใต้ ลักษณะเช่นนี้ทำให้บริเวณดังกล่าวมีฝนฟ้าคะนองเกิดขึ้นได้ในระยะนี้\n" +
                "\n" +
                "อนึ่ง บริเวณความกดอากาศสูงจากประเทศจีนได้แผ่ลงมาปกคลุมประเทศจีนตอนใต้และประเทศเวียดนามตอนบนแล้ว คาดว่าจะเข้าปกคลุมภาคตะวันออกเฉียงเหนือของประเทศไทย และทะเลจีนใต้ ในวันพรุ่งนี้ (27 เม.ย. 2560) และจะเข้าปกคลุมประเทศไทยตอนบนจนถึงวันที่ 29 เม.ย. 2560 ในขณะที่ประเทศไทยมีอากาศร้อน ลักษณะเช่นนี้ทำให้บริเวณดังกล่าวมีพายุฤดูร้อนเกิดขึ้นได้\n" +
                "\n" +
                "พยากรณ์อากาศสำหรับประเทศไทย\n" +
                "... อ่านต่อที่ : https://www.dailynews.co.th/regional/570535"));
        dataList.add(new NewsListModel("เตือนไทยตอนบนมีพายุฤดูร้อน กทม.ตก 40 % ","อุตุ เตือน 28-29 เมย.ไทยตอนบนมีพายุฤดูร้อน ฝนคะนอง ลมแรง ลูกเห็บตก ฟ้าผ่าบางแห่ง กทม.ตก 40 %ของพื้นที่ ..."));
        dataList.add(new NewsListModel("พายุฤดูร้อนถล่มโคราชวันเดียว บ้านเรือนเสียหาย300หลัง","พายุฤดูร้อนถล่มโคราช วันเดียว 9 อำเภอ บ้านเรือนเสียหายกว่า 300 หลังคาเรือน ผู้ว่าฯ เร่งสำรวจเพื่อให้การช่วยเหลือเบื้องต้นแล้ว..."));
        dataList.add(new NewsListModel("อุตุฯเตือนฉ.1พายุฤดูร้อนบริเวณไทยตอนบน","กรมอุตุนิยมวิทยา ออกประกาศฉบับที่ 1 เรื่อง 'พายุฤดูร้อนบริเวณประเทศไทยตอนบน'\n" +
                "\n" +
                "ในช่วงวันที่ 27-29 เมษายน 2560 บริเวณประเทศไทยตอนบนจะมีพายุฤดูร้อนเกิดขึ้น โดยมีพายุฝนฟ้าคะนองและลมกระโชกแรงเกิดขึ้นในหลายพื้นที่ กับมีลูกเห็บตกบางพื้นที่โดยเฉพาะในภาคเหนือและภาคตะวันออกเฉียงเหนือ และอากาศจะคลายความร้อนลง ซึ่งจะมีผลกระทบตามภาคต่างๆดังนี้\n" +
                "\n" +
                "วันที่ 27 เมษายน 2560 พายุฤดูร้อนจะเกิดขึ้นบริเวณด้านตะวันออกของภาคเหนือ ภาคตะวันออกเฉียงเหนือ และภาคตะวันออก\n" +
                "\n" +
                "วันที่ 28-29 เมษายน 2560 บริเวณที่เกิดพายุฤดูร้อนจะเพิ่มมากขึ้น และครอบคลุมบริเวณภาคเหนือ\n" +
                "\n" +
                "ภาคตะวันออกเฉียงเหนือ ภาคตะวันออก และภาคกลาง รวมทั้งกรุงเทพมหานครและปริมณฑล\n" +
                "\n" +
                "ขอให้ประชาชนหลีกเลี่ยงการอยู่ในที่โล่งแจ้ง ใต้ต้นไม้ใหญ่ และป้ายโฆษณาที่ไม่แข็งแรง ในขณะที่เกิดพายุฝนฟ้าคะนอง ลมกระโชกแรง ในช่วงวันและเวลาดังกล่าวไว้ด้วย"));

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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




}
