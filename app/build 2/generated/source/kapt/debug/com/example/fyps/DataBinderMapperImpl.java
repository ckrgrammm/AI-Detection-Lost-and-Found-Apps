package com.example.fyps;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.fyps.databinding.CartItemBindingImpl;
import com.example.fyps.databinding.FragmentAddUpdateQuestionBindingImpl;
import com.example.fyps.databinding.FragmentAdminAddRewardBindingImpl;
import com.example.fyps.databinding.FragmentAdminUpdateRewardBindingImpl;
import com.example.fyps.databinding.FragmentAdminViewRewardBindingImpl;
import com.example.fyps.databinding.FragmentCategoryDetailBindingImpl;
import com.example.fyps.databinding.FragmentPassedQuizzesBindingImpl;
import com.example.fyps.databinding.FragmentPlayBindingImpl;
import com.example.fyps.databinding.FragmentProductPreviewBindingImpl;
import com.example.fyps.databinding.FragmentQuestionBindingImpl;
import com.example.fyps.databinding.FragmentQuizBindingImpl;
import com.example.fyps.databinding.FragmentRedeemRewardBindingImpl;
import com.example.fyps.databinding.FragmentResultBindingImpl;
import com.example.fyps.databinding.FragmentRewardBindingImpl;
import com.example.fyps.databinding.FragmentRewardHistoryBindingImpl;
import com.example.fyps.databinding.FragmentSetsBindingImpl;
import com.example.fyps.databinding.FragmentTempRatingBindingImpl;
import com.example.fyps.databinding.GridLayoutBindingImpl;
import com.example.fyps.databinding.ItemMenuBindingImpl;
import com.example.fyps.databinding.ProductLayoutRowBindingImpl;
import com.example.fyps.databinding.RecyclerViewAllOrdersItemBindingImpl;
import com.example.fyps.databinding.RedeemRewardItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_CARTITEM = 1;

  private static final int LAYOUT_FRAGMENTADDUPDATEQUESTION = 2;

  private static final int LAYOUT_FRAGMENTADMINADDREWARD = 3;

  private static final int LAYOUT_FRAGMENTADMINUPDATEREWARD = 4;

  private static final int LAYOUT_FRAGMENTADMINVIEWREWARD = 5;

  private static final int LAYOUT_FRAGMENTCATEGORYDETAIL = 6;

  private static final int LAYOUT_FRAGMENTPASSEDQUIZZES = 7;

  private static final int LAYOUT_FRAGMENTPLAY = 8;

  private static final int LAYOUT_FRAGMENTPRODUCTPREVIEW = 9;

  private static final int LAYOUT_FRAGMENTQUESTION = 10;

  private static final int LAYOUT_FRAGMENTQUIZ = 11;

  private static final int LAYOUT_FRAGMENTREDEEMREWARD = 12;

  private static final int LAYOUT_FRAGMENTRESULT = 13;

  private static final int LAYOUT_FRAGMENTREWARD = 14;

  private static final int LAYOUT_FRAGMENTREWARDHISTORY = 15;

  private static final int LAYOUT_FRAGMENTSETS = 16;

  private static final int LAYOUT_FRAGMENTTEMPRATING = 17;

  private static final int LAYOUT_GRIDLAYOUT = 18;

  private static final int LAYOUT_ITEMMENU = 19;

  private static final int LAYOUT_PRODUCTLAYOUTROW = 20;

  private static final int LAYOUT_RECYCLERVIEWALLORDERSITEM = 21;

  private static final int LAYOUT_REDEEMREWARDITEM = 22;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(22);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.cart_item, LAYOUT_CARTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_add_update_question, LAYOUT_FRAGMENTADDUPDATEQUESTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_admin_add_reward, LAYOUT_FRAGMENTADMINADDREWARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_admin_update_reward, LAYOUT_FRAGMENTADMINUPDATEREWARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_admin_view_reward, LAYOUT_FRAGMENTADMINVIEWREWARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_category_detail, LAYOUT_FRAGMENTCATEGORYDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_passed_quizzes, LAYOUT_FRAGMENTPASSEDQUIZZES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_play, LAYOUT_FRAGMENTPLAY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_product_preview, LAYOUT_FRAGMENTPRODUCTPREVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_question, LAYOUT_FRAGMENTQUESTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_quiz, LAYOUT_FRAGMENTQUIZ);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_redeem_reward, LAYOUT_FRAGMENTREDEEMREWARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_result, LAYOUT_FRAGMENTRESULT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_reward, LAYOUT_FRAGMENTREWARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_reward_history, LAYOUT_FRAGMENTREWARDHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_sets, LAYOUT_FRAGMENTSETS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.fragment_temp_rating, LAYOUT_FRAGMENTTEMPRATING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.grid_layout, LAYOUT_GRIDLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.item_menu, LAYOUT_ITEMMENU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.product_layout_row, LAYOUT_PRODUCTLAYOUTROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.recycler_view_all_orders_item, LAYOUT_RECYCLERVIEWALLORDERSITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.fyps.R.layout.redeem_reward_item, LAYOUT_REDEEMREWARDITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_CARTITEM: {
          if ("layout/cart_item_0".equals(tag)) {
            return new CartItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for cart_item is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADDUPDATEQUESTION: {
          if ("layout/fragment_add_update_question_0".equals(tag)) {
            return new FragmentAddUpdateQuestionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_add_update_question is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADMINADDREWARD: {
          if ("layout/fragment_admin_add_reward_0".equals(tag)) {
            return new FragmentAdminAddRewardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_admin_add_reward is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADMINUPDATEREWARD: {
          if ("layout/fragment_admin_update_reward_0".equals(tag)) {
            return new FragmentAdminUpdateRewardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_admin_update_reward is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTADMINVIEWREWARD: {
          if ("layout/fragment_admin_view_reward_0".equals(tag)) {
            return new FragmentAdminViewRewardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_admin_view_reward is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCATEGORYDETAIL: {
          if ("layout/fragment_category_detail_0".equals(tag)) {
            return new FragmentCategoryDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_category_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPASSEDQUIZZES: {
          if ("layout/fragment_passed_quizzes_0".equals(tag)) {
            return new FragmentPassedQuizzesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_passed_quizzes is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPLAY: {
          if ("layout/fragment_play_0".equals(tag)) {
            return new FragmentPlayBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_play is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPRODUCTPREVIEW: {
          if ("layout/fragment_product_preview_0".equals(tag)) {
            return new FragmentProductPreviewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_product_preview is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTQUESTION: {
          if ("layout/fragment_question_0".equals(tag)) {
            return new FragmentQuestionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_question is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTQUIZ: {
          if ("layout/fragment_quiz_0".equals(tag)) {
            return new FragmentQuizBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_quiz is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREDEEMREWARD: {
          if ("layout/fragment_redeem_reward_0".equals(tag)) {
            return new FragmentRedeemRewardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_redeem_reward is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTRESULT: {
          if ("layout/fragment_result_0".equals(tag)) {
            return new FragmentResultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_result is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREWARD: {
          if ("layout/fragment_reward_0".equals(tag)) {
            return new FragmentRewardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_reward is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREWARDHISTORY: {
          if ("layout/fragment_reward_history_0".equals(tag)) {
            return new FragmentRewardHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_reward_history is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSETS: {
          if ("layout/fragment_sets_0".equals(tag)) {
            return new FragmentSetsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_sets is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTEMPRATING: {
          if ("layout/fragment_temp_rating_0".equals(tag)) {
            return new FragmentTempRatingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_temp_rating is invalid. Received: " + tag);
        }
        case  LAYOUT_GRIDLAYOUT: {
          if ("layout/grid_layout_0".equals(tag)) {
            return new GridLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for grid_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMENU: {
          if ("layout/item_menu_0".equals(tag)) {
            return new ItemMenuBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_menu is invalid. Received: " + tag);
        }
        case  LAYOUT_PRODUCTLAYOUTROW: {
          if ("layout/product_layout_row_0".equals(tag)) {
            return new ProductLayoutRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for product_layout_row is invalid. Received: " + tag);
        }
        case  LAYOUT_RECYCLERVIEWALLORDERSITEM: {
          if ("layout/recycler_view_all_orders_item_0".equals(tag)) {
            return new RecyclerViewAllOrdersItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for recycler_view_all_orders_item is invalid. Received: " + tag);
        }
        case  LAYOUT_REDEEMREWARDITEM: {
          if ("layout/redeem_reward_item_0".equals(tag)) {
            return new RedeemRewardItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for redeem_reward_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(7);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "adminUpdateRewardViewModel");
      sKeys.put(2, "courseDocument");
      sKeys.put(3, "playViewModel");
      sKeys.put(4, "productModel");
      sKeys.put(5, "questionViewModel");
      sKeys.put(6, "setsViewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(22);

    static {
      sKeys.put("layout/cart_item_0", com.example.fyps.R.layout.cart_item);
      sKeys.put("layout/fragment_add_update_question_0", com.example.fyps.R.layout.fragment_add_update_question);
      sKeys.put("layout/fragment_admin_add_reward_0", com.example.fyps.R.layout.fragment_admin_add_reward);
      sKeys.put("layout/fragment_admin_update_reward_0", com.example.fyps.R.layout.fragment_admin_update_reward);
      sKeys.put("layout/fragment_admin_view_reward_0", com.example.fyps.R.layout.fragment_admin_view_reward);
      sKeys.put("layout/fragment_category_detail_0", com.example.fyps.R.layout.fragment_category_detail);
      sKeys.put("layout/fragment_passed_quizzes_0", com.example.fyps.R.layout.fragment_passed_quizzes);
      sKeys.put("layout/fragment_play_0", com.example.fyps.R.layout.fragment_play);
      sKeys.put("layout/fragment_product_preview_0", com.example.fyps.R.layout.fragment_product_preview);
      sKeys.put("layout/fragment_question_0", com.example.fyps.R.layout.fragment_question);
      sKeys.put("layout/fragment_quiz_0", com.example.fyps.R.layout.fragment_quiz);
      sKeys.put("layout/fragment_redeem_reward_0", com.example.fyps.R.layout.fragment_redeem_reward);
      sKeys.put("layout/fragment_result_0", com.example.fyps.R.layout.fragment_result);
      sKeys.put("layout/fragment_reward_0", com.example.fyps.R.layout.fragment_reward);
      sKeys.put("layout/fragment_reward_history_0", com.example.fyps.R.layout.fragment_reward_history);
      sKeys.put("layout/fragment_sets_0", com.example.fyps.R.layout.fragment_sets);
      sKeys.put("layout/fragment_temp_rating_0", com.example.fyps.R.layout.fragment_temp_rating);
      sKeys.put("layout/grid_layout_0", com.example.fyps.R.layout.grid_layout);
      sKeys.put("layout/item_menu_0", com.example.fyps.R.layout.item_menu);
      sKeys.put("layout/product_layout_row_0", com.example.fyps.R.layout.product_layout_row);
      sKeys.put("layout/recycler_view_all_orders_item_0", com.example.fyps.R.layout.recycler_view_all_orders_item);
      sKeys.put("layout/redeem_reward_item_0", com.example.fyps.R.layout.redeem_reward_item);
    }
  }
}
