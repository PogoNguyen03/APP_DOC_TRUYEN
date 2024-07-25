package com.example.daocomics.ui.tabmain;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.adapter.ComicsAdapter;
import com.example.daocomics.model.Comic;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {
    EditText search;
    ImageView img;
    RecyclerView rcv;
    ArrayList<Comic> comicsList,comicsListtemp;
    GridLayoutManager grv;
    ComicsAdapter comicsAdapter, newAdapter;


    Context ct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View v =  inflater.inflate(R.layout.fragment_explore, container, false);
         search = v.findViewById(R.id.edSearch);
         img = v.findViewById(R.id.imgExploreLuffy);
         rcv = v.findViewById(R.id.rcvSearchList);
         img.setImageBitmap(Utils.convertFromAssets(getActivity(), "explore.png"));
         MainActivity temp = (MainActivity) getActivity();
         comicsAdapter = temp.getComicsAdapter();

         comicsList = comicsAdapter.getComicsList();

         comicsListtemp = new ArrayList<>();

         onSearch();

         newAdapter = new ComicsAdapter(temp);
         newAdapter.setComicsList(comicsListtemp);
         grv = new GridLayoutManager(getActivity(),2);

        return v;
    }

    private void onSearch() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = search.getText().toString();
                if(s.length()>0) {
                    img.setImageBitmap(Utils.convertFromAssets(getActivity(), "white.png"));
                    if(comicsListtemp.size()!=comicsList.size()) {
                        for (Comic c : comicsList) {
                            comicsListtemp.add(c);
                        }
                    }
                    newAdapter.SortComic(comicsList,s);
                }
                else {
                    comicsListtemp.clear();
                    img.setImageBitmap(Utils.convertFromAssets(getActivity(), "explore.png"));
                }
                rcv.setAdapter(newAdapter);
                rcv.setLayoutManager(grv);
            }
        });
    }

}