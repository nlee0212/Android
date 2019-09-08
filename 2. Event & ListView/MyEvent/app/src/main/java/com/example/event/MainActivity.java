package com.example.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);//has to be done in onCreate()

        View view=findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action=event.getAction();//to save what kind of action was done
                float curX = event.getX();//to save the x coordinate
                float curY = event.getY();//to save the y coordinate

                if(action == event.ACTION_DOWN)
                {
                    println("손가락 눌렸음 : " + curX + ", " + curY);
                }

                else if(action==event.ACTION_MOVE)
                {
                    println("손가락 움직임 : " + curX + ", " + curY);
                }

                else if(action==event.ACTION_UP)
                {
                    println("손가락 떼졌음 : " + curX + ", " + curY);
                }

                return true;
            }
        });

        //it has to be final since it has to be used in a inner class. with no 'final', detector is just a local variable in this onCreate().
        final GestureDetector detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress() 호출됨.");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp() 호출됨.");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll() 호출됨 : " + distanceX + ", " + distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출됨 : " + velocityX + ", " + velocityY);
                return true;
            }
        });

        View view2=findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);//using detector for gesture outputs. it uses 'event' that was given as a parameter.
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "시스템 [BACK]버튼이 눌렸습니다.",Toast.LENGTH_LONG).show();//show() 마지막에 꼭 써야함!!
            return true;
        }

        else
            return false;
    }

    public void println(String data){
        textView.append(data+"\n");//each time we use 'println', the words will be printed in the textView line by line
   }
}
