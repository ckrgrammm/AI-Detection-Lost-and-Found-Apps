// Generated by view binder compiler. Do not edit!
package com.example.fyps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.fyps.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final LinearLayout linearNews;

  @NonNull
  public final LinearLayout linearSearch;

  @NonNull
  public final RecyclerView recyclerViewMenu;

  @NonNull
  public final RecyclerView recyclerViewNews;

  @NonNull
  public final RelativeLayout relativeView;

  @NonNull
  public final RecyclerView rvSearch;

  @NonNull
  public final EditText searchText;

  @NonNull
  public final TextView searchTitle;

  private FragmentHomeBinding(@NonNull CoordinatorLayout rootView, @NonNull LinearLayout linearNews,
      @NonNull LinearLayout linearSearch, @NonNull RecyclerView recyclerViewMenu,
      @NonNull RecyclerView recyclerViewNews, @NonNull RelativeLayout relativeView,
      @NonNull RecyclerView rvSearch, @NonNull EditText searchText, @NonNull TextView searchTitle) {
    this.rootView = rootView;
    this.linearNews = linearNews;
    this.linearSearch = linearSearch;
    this.recyclerViewMenu = recyclerViewMenu;
    this.recyclerViewNews = recyclerViewNews;
    this.relativeView = relativeView;
    this.rvSearch = rvSearch;
    this.searchText = searchText;
    this.searchTitle = searchTitle;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.linear_news;
      LinearLayout linearNews = ViewBindings.findChildViewById(rootView, id);
      if (linearNews == null) {
        break missingId;
      }

      id = R.id.linear_search;
      LinearLayout linearSearch = ViewBindings.findChildViewById(rootView, id);
      if (linearSearch == null) {
        break missingId;
      }

      id = R.id.recyclerViewMenu;
      RecyclerView recyclerViewMenu = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewMenu == null) {
        break missingId;
      }

      id = R.id.recyclerViewNews;
      RecyclerView recyclerViewNews = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewNews == null) {
        break missingId;
      }

      id = R.id.relativeView;
      RelativeLayout relativeView = ViewBindings.findChildViewById(rootView, id);
      if (relativeView == null) {
        break missingId;
      }

      id = R.id.rv_search;
      RecyclerView rvSearch = ViewBindings.findChildViewById(rootView, id);
      if (rvSearch == null) {
        break missingId;
      }

      id = R.id.search_text;
      EditText searchText = ViewBindings.findChildViewById(rootView, id);
      if (searchText == null) {
        break missingId;
      }

      id = R.id.search_title;
      TextView searchTitle = ViewBindings.findChildViewById(rootView, id);
      if (searchTitle == null) {
        break missingId;
      }

      return new FragmentHomeBinding((CoordinatorLayout) rootView, linearNews, linearSearch,
          recyclerViewMenu, recyclerViewNews, relativeView, rvSearch, searchText, searchTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}