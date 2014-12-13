// Generated code from Butter Knife. Do not modify!
package com.wachira.symptomapp;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HomeActivity$$ViewInjector {
  public static void inject(Finder finder, final com.wachira.symptomapp.HomeActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296274, "method 'Checkinin'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.Checkinin();
        }
      });
  }

  public static void reset(com.wachira.symptomapp.HomeActivity target) {
  }
}
