package com.example.root.thub_login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Activities_fragment extends Fragment{
    String[] menuitems={"T-shaped Engineer","Code heat","Pupa","Sill up","Target","Hackathon","In campus Internship","Internet of things"
            ,"Project Hub","Women empowerment"
    };
    ArrayAdapter<String>listViewAdapter;
    FragmentManager fragmentManager;

    public Activities_fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_activities_fragment, container,false);
        final ListView listView=(ListView) view.findViewById(R.id.activityview);
        listViewAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,menuitems);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String selectedData=listViewAdapter.getItem(position);

                if(position==0){
                fragmentManager=getFragmentManager();

                    fragmentManager.beginTransaction().replace(R.id.main_container,new Projects_fragment()).commit();
                }
                if(position==1){
                fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_container,new Training_fragment()).commit();
                }
            }
        });
        return view;
    }


}
