package com.appministrator.synnectt.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appministrator.synnectt.R;
import com.appministrator.synnectt.models.event;
import com.appministrator.synnectt.utils.eventsRvAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DashboardFragment extends Fragment {

    private FirebaseFirestore fStore;
    private CollectionReference mEventsColRef;
    private eventsRvAdapter adapter;
    RecyclerView eventsRv;
    private static final String TAG = "DashboardFragment";

    public DashboardFragment() {
        firebaseInit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        eventsRv = v.findViewById(R.id.upEventsRv);
        setupRecyclerView(v);
        return v;
    }

    private void firebaseInit() {
        fStore = FirebaseFirestore.getInstance();
        mEventsColRef = fStore.collection("events");
    }

    private void setupRecyclerView(View view) {
        Query query = mEventsColRef;

        FirestoreRecyclerOptions<event> options = new FirestoreRecyclerOptions.Builder<event>()
                .setQuery(query, event.class).build();

        adapter = new eventsRvAdapter(options);

        eventsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        eventsRv.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: adapter starts listening");
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStart: adapter stopped listening");
        adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onStart: adapter starts listening");
        adapter.startListening();
    }
}