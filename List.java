package com.example.contactapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link List.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link List#newInstance} factory method to
 * create an instance of this fragment.
 */
public class List extends Fragment implements SearchView.OnQueryTextListener {

    private String USERNAME = null;
    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;
    MyCustomAdapter myAdapter;
    ListView lv;
    String[] arr;

    ListView myListView;
    SearchView svAllList;
    java.util.List<UserInfo> myUserList = new ArrayList<UserInfo>();
    String[] myMovies = {"A", "B", "C", "D", "A1", "B1", "C1", "D1", "A2", "B2", "C2", "D2", "A3", "B3", "C3", "D3", "A", "B", "C", "D", "A1", "B1", "C1", "D1", "A2", "B2", "C2", "D2", "A3", "B3", "C3", "D3"};


    public List(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
//        itemList = new ArrayList<String>();
//        lv = view.findViewById(R.id.list);
//        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getStringArrayList());
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                arr[position] = String.valueOf(100);
//                adapter.notifyDataSetChanged();
//            }
////            @Override
////            public void onClick(View v) {
////                itemList[p]
////            }
//        });
        svAllList = view.findViewById(R.id.svAllList);
        svAllList.setOnQueryTextListener(this);


        myListView = view.findViewById(R.id.list);

        myUserList.add(new UserInfo("Mulan", "To save her ailing Father.", R.drawable.mulon));
        myUserList.add(new UserInfo("Wonder Woman", "WonderW squares off against Maxwell.", R.drawable.wonderwoman));
        myUserList.add(new UserInfo("The Eternals", "The Sego of Eternals.", R.drawable.theeternals));
        myUserList.add(new UserInfo("The Visit", "The visit of my house.",  R.drawable.thevisit_));
        myUserList.add(new UserInfo("Texi Driver", "I sold my Text Today.",  R.drawable.thetaxi));
        myUserList.add(new UserInfo("King Kong", "Beast in love of Woman.",  R.drawable.kingkong));
//        myUserList.add(new UserInfo("Chris John", 28394372, R.drawable.chris));
//        myUserList.add(new UserInfo("Matt Demon", 83920348, R.drawable.damon));
//        myUserList.add(new UserInfo("The Rock", 81234590,  R.drawable.rock));
//        myUserList.add(new UserInfo("Will Smith", 94000394,  R.drawable.will));

        ///ArrayAdapter myAdapter = new ArrayAdapter(this, android.R.layout.c, myMovies);

        myAdapter = new MyCustomAdapter(view.getContext(), myUserList);
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

//                System.out.println("Display Index "+ i);
//
//                String valueUserClicked = myMovies[i];
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Add to Favourite List?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Favourite.insert_fav(myUserList.get(i));
                                Favourite.updateList();
                                dialog.dismiss();
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
//                System.out.println("Value from User  "+ valueUserClicked);

//                Intent newScreen = new Intent(getApplicationContext(), ListViewDetails.class);
//
//                newScreen.putExtra("value", valueUserClicked);
//
//                startActivity(newScreen);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private String[] getStringArrayList() {
        arr = new String[10];
        for(int i = 0;i<10;i++) {
            arr[i] = String.valueOf(i);
        }
        return arr;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        myAdapter.filter(text);
        return false;
    }
}
