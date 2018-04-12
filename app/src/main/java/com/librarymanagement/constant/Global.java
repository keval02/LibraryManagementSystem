package com.librarymanagement.constant;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.librarymanagement.R;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class Global {

    public static void CustomToast(Context context, String st) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_toast, null);

        TextView text = (TextView) view.findViewById(R.id.txt_message);
        text.setText(st);
        text.setTextSize(14);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);

        if (st != null && !st.trim().isEmpty()) toast.show();

    }

    public static boolean validateEmail(String emailId) {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{2,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,64}" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{2,6}");

        Matcher matcher = pattern.matcher(emailId);

        return matcher.matches();

    }

    public static void noInternet(Context context) {
        CustomToast(context, "No internet connection\nPlease try again later!");
    }

    public static void defaultError(Context context) {
        CustomToast(context, "Something went wrong\nPlease try again later!");
    }

    public static void errorToast(Context context, Throwable t) {
        if (t instanceof UnknownHostException || t instanceof ConnectException)
            noInternet(context);
        else
            defaultError(context);
    }

    public static boolean isMarshmallowPlusDevice() {

        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isPermissionRequestRequired(Activity activity, @NonNull String[] permissions, int requestCode) {
        if (isMarshmallowPlusDevice() && permissions.length > 0) {
            List<String> newPermissionList = new ArrayList<>();
            for (String permission : permissions) {
                if (PERMISSION_GRANTED != activity.checkSelfPermission(permission)) {
                    newPermissionList.add(permission);
                }
            }
            if (newPermissionList.size() > 0) {
                activity.requestPermissions(newPermissionList.toArray(new String[newPermissionList.size()]), requestCode);
                return true;
            }
        }

        return false;
    }


}
