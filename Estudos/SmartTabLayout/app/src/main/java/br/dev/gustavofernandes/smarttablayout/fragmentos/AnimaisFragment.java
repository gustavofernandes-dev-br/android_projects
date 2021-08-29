package br.dev.gustavofernandes.smarttablayout.fragmentos;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.dev.gustavofernandes.smarttablayout.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimaisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimaisFragment extends Fragment implements View.OnClickListener {

    ImageView imgDog;
    ImageView imgCat;
    ImageView imgMonkey;

    MediaPlayer media;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnimaisFragment() {
        // Required empty public constructor
    }


    void tocarSom()
    {
        if(media != null)
        {
            media.start();
            media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    media.release();
                }
            });
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoA.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimaisFragment newInstance(String param1, String param2) {
        AnimaisFragment fragment = new AnimaisFragment();
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
        View view = inflater.inflate(R.layout.fragment_animais, container, false);
        imgDog = view.findViewById(R.id.fragment_animais_cachorro);
        imgCat = view.findViewById(R.id.fragment_animais_gato);
        imgMonkey = view.findViewById(R.id.fragment_animais_macaco);

        imgCat.setOnClickListener(this);
        imgDog.setOnClickListener(this);
        imgMonkey.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fragment_animais_cachorro:
                media =  MediaPlayer.create(getActivity(),R.raw.dog);
                break;
            case R.id.fragment_animais_gato:
                media =  MediaPlayer.create(getActivity(),R.raw.cat);
                break;
            case R.id.fragment_animais_macaco:
                media =  MediaPlayer.create(getActivity(),R.raw.monkey);
                break;
        }
        tocarSom();
    }
}