package com.nowenui.systemtweaker.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.Utility;
import com.tt.whorlviewlibrary.WhorlView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WifiPasswordsFragment extends Fragment {
    WhorlView bar;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private ListView wifilistlistview;
    private TextView nomessage, sovetik;
    private ArrayList<Wifi> wifilist;
    private ListAdapter listAdapter;
    private Handler handler;

    public static WifiPasswordsFragment newInstance(Bundle bundle) {
        WifiPasswordsFragment WifiPasswords = new WifiPasswordsFragment();

        if (bundle != null) {
            WifiPasswords.setArguments(bundle);
        }

        return WifiPasswords;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wifipasswords, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        wifilistlistview = view.findViewById(R.id.wificodelist);
        nomessage = view.findViewById(R.id.nomessage);
        wifilist = new ArrayList<Wifi>();
        filldata();


        final SwipeRefreshLayout srl = view.findViewById(R.id.swipe);
        srl.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        wifilist = new ArrayList<Wifi>();
                        filldata();
                        srl.setRefreshing(false);
                    }
                });


    }

    private void filldata() {
        handler = new Handler();
        handler.postDelayed(new Thread() {
            @Override
            public void run() {
                super.run();
                dialogBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.wifi_dialog, null);
                dialogBuilder.setView(dialogView);

                bar = dialogView.findViewById(R.id.progress1);
                alertDialog = dialogBuilder.create();
                bar.start();
                bar.setVisibility(View.VISIBLE);
                wifilistlistview.setVisibility(View.GONE);
                alertDialog.setCancelable(false);
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.getWindow().setDimAmount(0);

            }
        }, 500);


        DataOutputStream dos = null;
        DataInputStream dis = null;
        String sourcefilename = "/data/misc/wifi/wpa_supplicant.conf";
        File f = new File(getActivity().getApplicationContext().getFilesDir(), "/wifi.conf");
        if (f.exists()) {
            System.out.println(f.delete());
        }
        try {
            Process p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());
            dis = new DataInputStream(p.getInputStream());
            dos.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system\n");
            dos.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system\n");
            dos.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system\n");
            dos.writeBytes("mount -o rw,remount /system\n");
            dos.writeBytes("cat " + sourcefilename + ">" + getActivity().getApplicationContext().getFilesDir() + "/wifi.conf \n");
            dos.writeBytes("chmod 774 " + getActivity().getApplicationContext().getFilesDir() + "/wifi.conf \n");
            dos.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
            dos.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
            dos.writeBytes("mount -o ro,remount /system\n");
            dos.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
            dos.writeBytes("exit\n");
            getfile();
            dos.flush();
            dos.close();
            dis.close();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler = new Handler();
        handler.postDelayed(new Thread() {
            @Override
            public void run() {
                super.run();
                getfile();
            }
        }, 6000);
    }


    private void getfile() {
        DataInputStream dis = null;
        File f = new File(getActivity().getApplicationContext().getFilesDir(), "/wifi.conf");
        try {
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                StringBuffer result = new StringBuffer();
                String s = "";
                while ((s = br.readLine()) != null) {
                    result.append(s);
                }
                String wifiresult = result.toString();
                Pattern p = Pattern.compile("network=\\{(.*?)\\}");
                Matcher m = p.matcher(wifiresult);
                while (m.find()) {
                    Wifi wmsg = new Wifi();
                    String line = m.group(1);
                    if (line.contains("ssid=\"") && line.contains("psk=\"")) {
                        Pattern k = Pattern.compile("ssid=\"(.*?)\\\"");
                        Matcher w = k.matcher(line);
                        if (w.find()) {
                            String i = w.group(1);
                            wmsg.wifiName = i;
                        }
                        Pattern q = Pattern.compile("psk=\"(.*?)\\\"");
                        Matcher o = q.matcher(line);
                        if (o.find()) {
                            String j = o.group(1);
                            wmsg.wifiCode = j;
                        }
                        wifilist.add(wmsg);
                    }
                    System.out.println(line + "\n");
                }
                br.close();
                if (wifilist.isEmpty()) {
                    alertDialog.cancel();
                    bar.setVisibility(View.GONE);
                    wifilistlistview.setVisibility(View.GONE);
                    bar.stop();
                    nomessage.setVisibility(View.VISIBLE);
                    nomessage.setText(R.string.nowifisaved);
                } else {
                    alertDialog.cancel();
                    bar.setVisibility(View.GONE);
                    wifilistlistview.setVisibility(View.VISIBLE);
                    listAdapter = new wifilistAdapter(getActivity().getApplicationContext(), wifilist);
                    wifilistlistview.setAdapter(listAdapter);
                    wifilistlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView tvwificode = view
                                    .findViewById(R.id.wificode);
                            setClipboard(getContext(), tvwificode.getText().toString());
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.passwdcopied)).withBackgroundColorId(R.color.textview1good).show();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Wifi copied", text);
        clipboard.setPrimaryClip(clip);
    }

    private class wifilistAdapter extends BaseAdapter {
        private ArrayList<Wifi> list = null;
        private Context context;

        public wifilistAdapter(Context mContext,
                               ArrayList<Wifi> list) {
            this.context = mContext;
            this.list = list;
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            Wifi wifimsg;
            View view;
            wifimsg = list.get(position);
            if (convertView == null) {
                view = View.inflate(context, R.layout.item_wifilist, null);
                holder = new ViewHolder();
                holder.tvwifiname = view
                        .findViewById(R.id.wifiname);
                holder.tvwificode = view
                        .findViewById(R.id.wificode);
                holder.wificodetext = view
                        .findViewById(R.id.wificodetext);
                holder.wifitext = view
                        .findViewById(R.id.wifitext);
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    holder.tvwifiname.setTextColor(Color.parseColor("#2d2d2d"));
                    holder.tvwificode.setTextColor(Color.parseColor("#2d2d2d"));
                    holder.wificodetext.setTextColor(Color.parseColor("#2d2d2d"));
                    holder.wifitext.setTextColor(Color.parseColor("#2d2d2d"));
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    holder.tvwifiname.setTextColor(Color.WHITE);
                    holder.tvwificode.setTextColor(Color.WHITE);
                    holder.wificodetext.setTextColor(Color.WHITE);
                    holder.wifitext.setTextColor(Color.WHITE);
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    holder.tvwifiname.setTextColor(Color.WHITE);
                    holder.tvwificode.setTextColor(Color.WHITE);
                    holder.wificodetext.setTextColor(Color.WHITE);
                    holder.wifitext.setTextColor(Color.WHITE);
                }
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            if (!TextUtils.isEmpty(wifimsg.wifiName)) {
                holder.tvwifiname.setText(wifimsg.wifiName);
            }
            if (!TextUtils.isEmpty(wifimsg.wifiCode)) {
                holder.tvwificode.setText(wifimsg.wifiCode);
            }
            return view;
        }
    }

    private class ViewHolder {
        TextView wifitext;
        TextView wificodetext;
        TextView tvwifiname;
        TextView tvwificode;
    }

}