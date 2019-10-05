package com.example.swu_home.activity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.swu_home.R;
import com.example.swu_home.fragment.FragmentAlarm;
import com.example.swu_home.fragment.FragmentSetting;
import com.example.swu_home.fragment.FragmentSit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.TimeUnit;

//블루투스
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//블루투스2
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

import static java.sql.Types.NULL;

//메시지
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.inputmethod.InputMethodManager;


/*import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;*/

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ViewPager menuViewPager;

    private FragmentSit fragmentSit;
    private FragmentAlarm fragmentAlarm;
    private FragmentSetting fragmentSetting;
    MenuItem prevMenuItem;

    //블루투스
    private static final int REQUEST_ENABLE_BT = 10; // 블루투스 활성화 상태
    private BluetoothAdapter bluetoothAdapter; // 블루투스 어댑터
    private Set<BluetoothDevice> devices; // 블루투스 디바이스 데이터 셋
    private BluetoothDevice bluetoothDevice; // 블루투스 디바이스
    private BluetoothSocket bluetoothSocket = null; // 블루투스 소켓
    private OutputStream outputStream = null; // 블루투스에 데이터를 출력하기 위한 출력 스트림
    private InputStream inputStream = null; // 블루투스에 데이터를 입력하기 위한 입력 스트림
    private Thread workerThread = null; // 문자열 수신에 사용되는 쓰레드
    private byte[] readBuffer; // 수신 된 문자열을 저장하기 위한 버퍼
    private int readBufferPosition; // 버퍼 내 문자 저장 위치
    private TextView textViewReceive; // 수신 된 데이터를 표시하기 위한 텍스트 뷰
    private EditText editTextSend; // 송신 할 데이터를 작성하기 위한 에딧 텍스트
    private Button buttonSend; // 송신하기 위한 버튼

    private int bluestate=0;
    String select_led;
    String data;


//메시지
    final InputMethodManager[] imm = new InputMethodManager[1];
    final PendingIntent sentPI = null;
    int fire = 0;
    String red= "r";
    String green= "e";
    String yellow= "y";
//    int red = 5;
//    int green = 6;
//    int yellow =7;

    //블루투스2
    private BluetoothSPP bt;

    Intent setIntent= getIntent();
    ImageButton imagebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //veiwpager 설정
        menuViewPager = findViewById(R.id.viewPager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navi);
        //sitset값 받아오기
        setIntent = getIntent();
        select_led =  setIntent.getStringExtra("select_color");

        //imagebtn = (ImageButton)findViewById(R.id.)
        // 값넘어옴.
        if(select_led != null) {
            Log.d("ledcolor", select_led);
           //changeLed(select_led);

        }





        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        menuViewPager.setCurrentItem(0);
                        break;

                    case R.id.action_alarm:
                        menuViewPager.setCurrentItem(1);
                        break;

                    case R.id.action_contact:
                        menuViewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }

        });

        menuViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }


            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setupViewPager(menuViewPager);


        // 블루투스 활성화하기
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // 블루투스 어댑터를 디폴트 어댑터로 설정
        if (bluetoothAdapter == null) { // 디바이스가 블루투스를 지원하지 않을 때
            // 여기에 처리 할 코드를 작성하세요.

        } else { // 디바이스가 블루투스를 지원 할 때
            if (bluetoothAdapter.isEnabled()) { // 블루투스가 활성화 상태 (기기에 블루투스가 켜져있음)
                selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
            } else { // 블루투스가 비 활성화 상태 (기기에 블루투스가 꺼져있음)
                // 블루투스를 활성화 하기 위한 다이얼로그 출력

                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                // 선택한 값이 onActivityResult 함수에서 콜백된다.

                startActivityForResult(intent, REQUEST_ENABLE_BT); //REQUEST_ENABLE_BT

            }
        }
/*
        //블루투스2
        bt = new BluetoothSPP(this); //Initializing

        if (!bt.isBluetoothAvailable()) { //블루투스 사용 불가
            Toast.makeText(getApplicationContext()
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();
            finish();
        }

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신
            public void onDataReceived(byte[] data, String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getApplicationContext()
                        , "Connection lost", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });

        if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
            //bt.connect("98:D3:51:FD:76:53");
        } else {
            onStart();
            Intent intent = new Intent(getApplicationContext(), DeviceList.class);
            startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
        }
*/

    }




    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentSit=new FragmentSit();
        fragmentAlarm=new FragmentAlarm();
        fragmentSetting=new FragmentSetting();

        adapter.addFragment(fragmentSit);
        adapter.addFragment(fragmentAlarm);
        adapter.addFragment(fragmentSetting);
        viewPager.setAdapter(adapter);
    }





    private long backPressedAt;

    @Override
    public void onBackPressed() {
        if (backPressedAt + TimeUnit.SECONDS.toMillis(2) > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        }
        else {
            if(this instanceof MainActivity) {
                Toast.makeText(this, "한번더 뒤로가기 클릭시 앱을 종료 합니다.", Toast.LENGTH_SHORT).show();
                backPressedAt = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    //블루투스

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BT :
                if(requestCode == RESULT_OK) { // '사용'을 눌렀을 때
                    selectBluetoothDevice(); // 블루투스 디바이스 선택 함수 호출
                }
                else { // '취소'를 눌렀을 때
                    // 여기에 처리 할 코드를 작성하세요.

                }
                break;
        }
    }

    public void selectBluetoothDevice() {/////

        // 이미 페어링 되어있는 블루투스 기기를 찾습니다.

        devices = bluetoothAdapter.getBondedDevices();

        // 페어링 된 디바이스의 크기를 저장

        int pariedDeviceCount = devices.size();

        // 페어링 되어있는 장치가 없는 경우

        if(pariedDeviceCount == 0) {

            // 페어링을 하기위한 함수 호출

        }

        // 페어링 되어있는 장치가 있는 경우

        else {
            ImageButton blue = findViewById(R.id.bluetooth);
            blue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dia();
                }
            });



        }
    }

    public void dia(){
        if(bluestate==0){
            bluestate=1;
            // 디바이스를 선택하기 위한 다이얼로그 생성

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("페어링 되어있는 블루투스 디바이스 목록");

            // 페어링 된 각각의 디바이스의 이름과 주소를 저장

            List<String> list = new ArrayList<>();

            // 모든 디바이스의 이름을 리스트에 추가

            for(BluetoothDevice bluetoothDevice : devices) {

                list.add(bluetoothDevice.getName());

            }

            list.add("취소");




            // List를 CharSequence 배열로 변경

            final CharSequence[] charSequences = list.toArray(new CharSequence[list.size()]);

            list.toArray(new CharSequence[list.size()]);




            // 해당 아이템을 눌렀을 때 호출 되는 이벤트 리스너

            builder.setItems(charSequences, new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which) {

                    // 해당 디바이스와 연결하는 함수 호출

                    connectDevice(charSequences[which].toString());

                }

            });

            // 뒤로가기 버튼 누를 때 창이 안닫히도록 설정
            builder.setCancelable(false);
            // 다이얼로그 생성
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }else{
            bluestate=0;
        }
    }

    public void connectDevice(String deviceName) {
        // 페어링 된 디바이스들을 모두 탐색
        for(BluetoothDevice tempDevice : devices) {
            // 사용자가 선택한 이름과 같은 디바이스로 설정하고 반복문 종료
            if(deviceName.equals(tempDevice.getName())) {
                bluetoothDevice = tempDevice;
                break;
            }
        }
        // UUID 생성
        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        // Rfcomm 채널을 통해 블루투스 디바이스와 통신하는 소켓 생성
        try {
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();

            // 데이터 송,수신 스트림을 얻어옵니다.
            outputStream = bluetoothSocket.getOutputStream();
            inputStream = bluetoothSocket.getInputStream();

            // 데이터 수신 함수 호출
            receiveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveData() {
        final Handler handler = new Handler();
        // 데이터를 수신하기 위한 버퍼를 생성
        readBufferPosition = 0;
        readBuffer = new byte[1024];

        // 데이터를 수신하기 위한 쓰레드 생성
        workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!(Thread.currentThread().isInterrupted())){ //
                    try {
                        // 데이터를 수신했는지 확인합니다.
                        int byteAvailable = inputStream.available();

                        // 데이터가 수신 된 경우
                        if(byteAvailable > 0) {

                            // 입력 스트림에서 바이트 단위로 읽어 옵니다.
                            byte[] bytes = new byte[byteAvailable];
                            inputStream.read(bytes);

                            // 입력 스트림 바이트를 한 바이트씩 읽어 옵니다.
                            for(int i = 0; i < byteAvailable; i++) {
                                byte tempByte = bytes[i];

                                // 개행문자를 기준으로 받음(한줄)
                                if(tempByte == '\n') {

                                    // readBuffer 배열을 encodedBytes로 복사
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);

                                    // 인코딩 된 바이트 배열을 문자열로 변환
                                    final String text = new String(encodedBytes, "US-ASCII").trim();
                                    readBufferPosition = 0;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            // 텍스트 뷰에 출력
                                            //textViewReceive.append(text + "\n");
                                            //textViewReceive.append("dkssud"+ "\n");
                                            //Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
                                            data=text;
                                            if(data.equals("b")){//초인종
                                                //Vibrator a = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                                                //a.vibrate(3000);
                                                sendData(yellow);
                                                Intent intent = new Intent(getApplicationContext(),AlertBellActivity.class);
                                                startActivity(intent);
                                            }else if(data.equals("f")){ //화재
                                                sendData(red);
                                                if(fire==0){
                                                    //sitsat에서 알림 체크여부값에 따라 전화번호 정보 문자 보내기
                                                    String sm = "불났어요.";
                                                    String phone = "01044814244";
                                                    fire=1;

                                                    imm[0] = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                                                    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS);
                                                    if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
                                                        Toast.makeText(getApplicationContext(), "권한을 허용하고 재전송해주세요", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        SmsManager smsManager = SmsManager.getDefault();

                                                        // 아래 구문으로 지정된 핸드폰으로 문자 메시지를 보낸다
                                                        smsManager.sendTextMessage(phone, null, sm, sentPI, null);
                                                        Toast.makeText(getApplicationContext(), "전송을 완료하였습니다", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                //sendSMS(phone, sm);
                                                //SmsManager smsManager = SmsManager.getDefault();
                                                //smsManager.sendTextMessage(phone, null, sm, null, null);
                                                Intent intent = new Intent(getApplicationContext(),AlertFireActivity.class);
                                                startActivity(intent);
                                            }else if(data.equals("d")){//세탁기
                                                sendData(yellow);
                                                Intent intent = new Intent(getApplicationContext(),AlertLaundryActivity.class);
                                                startActivity(intent);
                                            }else if(data.equals("t")) {//아기 울음소리
                                                sendData(green);
                                                Intent intent = new Intent(getApplicationContext(),AlertBabyActivity.class);
                                                startActivity(intent);

                                            }else if(data.equals("g")){//방문 노크
                                                sendData(green);
                                                Intent intent = new Intent(getApplicationContext(),AlertRoomActivity.class);
                                                startActivity(intent);

                                            }


                                        }
                                    });

                                } // 개행 문자가 아닐 경우

                                else {
                                    readBuffer[readBufferPosition++] = tempByte;
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        // 1초마다 받아옴
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        workerThread.start();
    }
/*
    private void sendSMS(String phone, String sm) {
        String SENT = "SNS_SENT";
        String DELIVERED = "SNS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT),0);
        PendingIntent deliverPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED),0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(),"알림 문자 메시지가 전송되었습니다.",Toast.LENGTH_LONG).show();
                    break;
                }
            }
        });
        new IntentFilter(SENT);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone,null,sm,sentPI,deliverPI);
    }
*/
    void sendData(String text) {
        // 문자열에 개행문자("\n")를 추가해줍니다.
        //text += "\n";

        try{
            // 데이터 송신
            outputStream.write(text.getBytes());
        }catch(Exception e) {

            e.printStackTrace();
        }

    }

/*    void changeLed (String select_led) {
        if(select_led.equals("빨간색")) {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_red));
        }

        if(select_led.equals("초록색")) {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_green));
        }

        if(select_led.equals("노란색")) {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_yellow));
        }
        else {
            imagebtn.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border_grey));
        }
    }*/

/*
    //블루투스2
    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) { //
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER); //DEVICE_ANDROID는 안드로이드 기기 끼리
                setup();
            }
        }
    }

    public void setup() {//데이터 전송
        bt.send("Text", true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
*/

}