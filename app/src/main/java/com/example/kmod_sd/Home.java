package com.example.kmod_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Home extends AppCompatActivity {

    String lcpuwp="chmod 664 /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpuperf="echo performance >  /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpups="echo powersave >  /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpusu="echo schedutil >  /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpurp="chmod 444 /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String pcpuwp="chmod 664 /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpuperf="echo performance >  /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpups="echo powersave >  /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpusu="echo schedutil >  /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpurp="chmod 444 /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String one="echo 1 >";
    String ofe="echo 0 >";
    String cpuoh1="/sys/devices/system/cpu/cpu";
    String cpuoh2="/online";
Button ref;
    Button ad;

    TextView[] cv=new TextView[2];
    TextView[] cpus=new TextView[8];
    Button b1,b2,b3,b4;

    public String executeCommand(String command) {

        StringBuilder output = new StringBuilder();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }


    @SuppressLint("SetTextI18n")
    void Refresh(TextView[] cv, TextView[] cp){
        // String executeCommand()
        String cpustat;
for (int i =0 ; i<8;i++){
    cpustat=executeCommand("su -c cat /sys/devices/system/cpu/cpu"+i+"/online");
    if(cpustat.contains("1")) {
        cp[i].setText("Online");
    }else if (cpustat.contains("0")){
        cp[i].setText("offline");
    }else{
        cp[i].setText("unknown");
    }
    cpustat=executeCommand("su -c cat /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor");
    cv[0].setText(cpustat);
    cpustat=executeCommand("su -c cat /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor");
    cv[1].setText(cpustat);
}

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ref=(Button)findViewById(R.id.refre);
        cv[0]=(TextView)findViewById(R.id.currentmode1) ;
        cv[1]=(TextView)findViewById(R.id.currentmode2) ;
        ad=(Button)findViewById(R.id.button2);

        cpus[0]=(TextView)findViewById(R.id.cpuv1) ;
        cpus[1]=(TextView)findViewById(R.id.cpuv2) ;
        cpus[2]=(TextView)findViewById(R.id.cpuv3) ;
        cpus[3]=(TextView)findViewById(R.id.cpuv4) ;
        cpus[4]=(TextView)findViewById(R.id.cpuv5) ;
        cpus[5]=(TextView)findViewById(R.id.cpuv6) ;
        cpus[6]=(TextView)findViewById(R.id.cpuv7) ;
        cpus[7]=(TextView)findViewById(R.id.cpuv8) ;
        b1=(Button)findViewById(R.id.bm1);
        b2=(Button)findViewById(R.id.bm2);
        b3=(Button)findViewById(R.id.bm3);
        b4=(Button)findViewById(R.id.bm4);

        Refresh(cv,cpus);
       ref.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // fun("echo \"hello working its disgust \" > /sdcard/wowowow");
Refresh(cv,cpus);
           }
       });
ad.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"Comming son",Toast.LENGTH_SHORT).show();
        //   Toast.makeText(this, "Comming son", Toast.LENGTH_SHORT).show();
    }
});
/*
  String lcpuwp="chmod 664 /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpuperf="echo performance >  /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpups="echo powersave >  /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpusu="echo schedutil >  /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String lcpurp="chmod 444 /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";
    String pcpuwp="chmod 664 /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpuperf="echo performance >  /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpups="echo powersave >  /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpusu="echo schedutil >  /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String pcpurp="chmod 444 /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor";
    String one="echo 1 > ";
    String ofe="echo 0 > ";
    String cpuoh1="/sys/devices/system/cpu/cpu";
    String cpuoh2="/online";

 */
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // string  executeCommand(cmd)
                for(int i=0;i<8;i++){
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);
                    executeCommand("su -c "+one+cpuoh1+i+cpuoh2);
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);

                }
                executeCommand("su -c" + lcpuwp);
                executeCommand("su -c "+lcpusu);
                executeCommand("su -c" + lcpurp);
                executeCommand("su -c" + pcpuwp);
                executeCommand("su -c "+pcpusu);
                executeCommand("su -c" + pcpurp);
                Refresh(cv,cpus);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<8;i++){
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);
                    executeCommand("su -c "+one+cpuoh1+i+cpuoh2);
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);

                }
                executeCommand("su -c" + lcpuwp);
                executeCommand("su -c "+lcpuperf);
                executeCommand("su -c" + lcpurp);
                executeCommand("su -c" + pcpuwp);
                executeCommand("su -c "+pcpuperf);
                executeCommand("su -c" + pcpurp);
                Refresh(cv,cpus);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeCommand("su -c" + lcpuwp);
                executeCommand("su -c "+lcpusu);
                executeCommand("su -c" + lcpurp);
                executeCommand("su -c" + pcpuwp);
                executeCommand("su -c "+pcpusu);
                executeCommand("su -c" + pcpurp);

                for(int i=0;i<3;i++){
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);
                    executeCommand("su -c "+one+cpuoh1+i+cpuoh2);
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);

                }
                for(int i=3;i<7;i++){
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);
                    executeCommand("su -c "+ofe+cpuoh1+i+cpuoh2);
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);

                }
                executeCommand("su -c chmod 664 " + cpuoh1 +"7"+cpuoh2);
                executeCommand("su -c "+one+cpuoh1+"7"+cpuoh2);
                executeCommand("su -c chmod 664 " + cpuoh1 +"7"+cpuoh2);

                Refresh(cv,cpus);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeCommand("su -c" + lcpuwp);
                executeCommand("su -c "+lcpusu);
                executeCommand("su -c" + lcpurp);
                executeCommand("su -c" + pcpuwp);
                executeCommand("su -c "+pcpusu);
                executeCommand("su -c" + pcpurp);

                for(int i=1;i<7;i++){
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);
                    executeCommand("su -c "+ofe+cpuoh1+i+cpuoh2);
                    executeCommand("su -c chmod 664 " + cpuoh1 +i+cpuoh2);

                }
                executeCommand("su -c chmod 664 " + cpuoh1 +"1"+cpuoh2);
                executeCommand("su -c "+one+cpuoh1+"1"+cpuoh2);
                executeCommand("su -c chmod 664 " + cpuoh1 +"1"+cpuoh2);
                executeCommand("su -c chmod 664 " + cpuoh1 +"7"+cpuoh2);
                executeCommand("su -c "+one+cpuoh1+"7"+cpuoh2);
                executeCommand("su -c chmod 664 " + cpuoh1 +"7"+cpuoh2);
                Refresh(cv,cpus);
            }
        });

    }
}
