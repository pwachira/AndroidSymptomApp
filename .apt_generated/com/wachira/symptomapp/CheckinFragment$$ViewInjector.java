// Generated code from Butter Knife. Do not modify!
package com.wachira.symptomapp;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CheckinFragment$$ViewInjector {
  public static void inject(Finder finder, final com.wachira.symptomapp.CheckinFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296261, "field 'spinnerqpain'");
    target.spinnerqpain = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296266, "field 'submitCheckin' and method 'submitCheckin'");
    target.submitCheckin = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.submitCheckin();
        }
      });
    view = finder.findRequiredView(source, 2131296263, "field 'spinnerqeating'");
    target.spinnerqeating = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296265, "field 'switchqmeds'");
    target.switchqmeds = (android.widget.Switch) view;
  }

  public static void reset(com.wachira.symptomapp.CheckinFragment target) {
    target.spinnerqpain = null;
    target.submitCheckin = null;
    target.spinnerqeating = null;
    target.switchqmeds = null;
  }
}
