// Generated code from Butter Knife. Do not modify!
package com.wachira.symptomapp;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginScreen$$ViewInjector {
  public static void inject(Finder finder, final com.wachira.symptomapp.LoginScreen target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165188, "field 'passwd'");
    target.passwd = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131165186, "field 'uname'");
    target.uname = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131165189, "method 'PatientLogin'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.PatientLogin();
        }
      });
    view = finder.findRequiredView(source, 2131165190, "method 'DocLogin'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.DocLogin();
        }
      });
  }

  public static void reset(com.wachira.symptomapp.LoginScreen target) {
    target.passwd = null;
    target.uname = null;
  }
}
