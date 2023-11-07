package com.gzrio.spacetimerunnertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gzrio.spacetimerunnertimer.Helper.UnitHelper;

public class MainActivity extends AppCompatActivity {
    UnitHelper unitHelper = new UnitHelper(MainActivity.this);

    ConstraintLayout constLayoutParent;
    ImageView imgMegaman;
    ImageView imgEarth;
    TextView lblTitle;
    TextView lblDesc;
    Button btnStart;
    TextView lblTimeLeft;

    Megaman megaman;
    private int timerDuration = 1000 * 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constLayoutParent = findViewById(R.id.constLayoutParent);
        imgMegaman = findViewById(R.id.imgMegaman);
        imgEarth = findViewById(R.id.imgEarth);
        lblTitle = findViewById(R.id.lblTitle);
        lblDesc = findViewById(R.id.lblDesc);
        btnStart = findViewById(R.id.btnStart);
        lblTimeLeft = findViewById(R.id.lblTimeLeft);

        megaman = new Megaman(imgMegaman, MainActivity.this);
        megaman.Idle();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblTitle
                        .animate()
                        .alpha(0)
                        .setDuration(500)
                        .setInterpolator(new DecelerateInterpolator());
                lblDesc
                        .animate()
                        .alpha(0)
                        .setDuration(500)
                        .setInterpolator(new DecelerateInterpolator());
                btnStart
                        .animate()
                        .alpha(0)
                        .setDuration(500)
                        .setInterpolator(new DecelerateInterpolator());

                megaman.Pose(new Runnable() {
                    @Override
                    public void run() {
                        imgMegaman
                                .animate()
                                .scaleX(0f)
                                .scaleY(0f)
                                .alpha(0)
                                .setDuration(300)
                                .setInterpolator(new DecelerateInterpolator())
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        imgEarth
                                                .animate()
                                                .scaleX(2f)
                                                .scaleY(2f)
                                                .translationX(unitHelper.DPToPixel(80))
                                                .translationY(constLayoutParent.getHeight() / 4f)
                                                .setDuration(500)
                                                .setInterpolator(new DecelerateInterpolator())
                                                .withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        imgEarth
                                                                .animate()
                                                                .translationY(0)
                                                                .translationX(0)
                                                                .scaleX(1f)
                                                                .scaleY(1f)
                                                                .setDuration(500)
                                                                .setInterpolator(new AccelerateInterpolator())
                                                                .withEndAction(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        imgMegaman.setTranslationY(0);

                                                                        lblTimeLeft
                                                                                .animate()
                                                                                .scaleX(1)
                                                                                .scaleY(1)
                                                                                .setDuration(300)
                                                                                .setInterpolator(new AccelerateDecelerateInterpolator());

                                                                        imgMegaman
                                                                                .animate()
                                                                                .alpha(1)
                                                                                .scaleX(1f)
                                                                                .scaleY(1f)
                                                                                .y(imgEarth.getY() - imgMegaman.getHeight() + unitHelper.DPToPixel(12))
                                                                                .setDuration(300)
                                                                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                                                                .withEndAction(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        megaman.Run();

                                                                                        StartTimer();
                                                                                    }
                                                                                });
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });
                    }
                });
            }
        });

        constLayoutParent.post(new Runnable() {
            @Override
            public void run() {
                imgEarth.setScaleX(1.3f);
                imgEarth.setScaleY(1.3f);

                imgMegaman.setScaleX(2.5f);
                imgMegaman.setScaleY(2.5f);

                imgEarth.setTranslationY(constLayoutParent.getHeight() / 2f);
                imgMegaman.setTranslationY((constLayoutParent.getHeight() / 2f) - imgMegaman.getHeight() - unitHelper.DPToPixel(12));

                lblTimeLeft.setScaleX(0);
                lblTimeLeft.setScaleY(0);
            }
        });
    }

    private void StartTimer() {
        imgMegaman.setPivotX(imgMegaman.getWidth() / 2f);
        imgMegaman.setPivotY((imgEarth.getHeight() / 2f) + imgMegaman.getHeight() - unitHelper.DPToPixel(12));

        imgMegaman
                .animate()
                .setDuration(timerDuration)
                .rotationBy(360)
                .setInterpolator(new LinearInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        megaman.Idle();
                    }
                });

        imgEarth
                .animate()
                .setDuration(timerDuration)
                .rotationBy(-45)
                .setInterpolator(new LinearInterpolator());

        TimerTick(timerDuration / 1000);
    }

    private void TimerTick(int durationLeft) {
        int minutes = durationLeft / 60;
        int seconds = durationLeft - (minutes * 60);
        lblTimeLeft.setText(String.format("%2s:%2s", minutes, seconds).replace(" ", "0"));

        if(durationLeft == 0) {
            lblTimeLeft.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AlertTimeIsUp(3);
                }
            }, 500);
            return;
        }

        lblTimeLeft.postDelayed(new Runnable() {
            @Override
            public void run() {
                TimerTick(durationLeft - 1);
            }
        }, 1000);
    }

    private void AlertTimeIsUp(int repeatCount) {
        if(repeatCount == 0) {
            ResetTimer();
            return;
        }

        lblTimeLeft.setText("Time's Up");
        lblTimeLeft
                .animate()
                .alpha(0)
                .setDuration(250)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        lblTimeLeft
                                .animate()
                                .alpha(1)
                                .setDuration(250)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertTimeIsUp(repeatCount - 1);
                                    }
                                });
                    }
                });
    }

    private void ResetTimer() {
        imgMegaman.setPivotX(imgMegaman.getWidth() / 2f);
        imgMegaman.setPivotY(imgMegaman.getHeight() / 2f);

        imgMegaman
                .animate()
                .scaleX(0)
                .scaleY(0)
                .setDuration(300)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        imgEarth
                                .animate()
                                .scaleX(2f)
                                .scaleY(2f)
                                .translationX(unitHelper.DPToPixel(80))
                                .translationY(constLayoutParent.getHeight() / 4f)
                                .rotation(0)
                                .setDuration(500)
                                .setInterpolator(new DecelerateInterpolator())
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        imgEarth
                                                .animate()
                                                .scaleX(1.3f)
                                                .scaleY(1.3f)
                                                .translationX(0f)
                                                .translationY(constLayoutParent.getHeight() / 2f)
                                                .setDuration(500)
                                                .setInterpolator(new DecelerateInterpolator())
                                                .withEndAction(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        imgMegaman
                                                                .animate()
                                                                .scaleX(2.5f)
                                                                .scaleY(2.5f)
                                                                .translationY((constLayoutParent.getHeight() / 2f) - imgMegaman.getHeight() - unitHelper.DPToPixel(12))
                                                                .setDuration(300)
                                                                .setInterpolator(new DecelerateInterpolator());
                                                    }
                                                });
                                    }
                                });
                    }
                });

        lblTitle
                .animate()
                .alpha(1)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator());
        lblDesc
                .animate()
                .alpha(1)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator());
        btnStart
                .animate()
                .alpha(1)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator());

        lblTimeLeft
                .animate()
                .scaleX(0)
                .scaleY(0)
                .setDuration(300)
                .setInterpolator(new AccelerateDecelerateInterpolator());
        lblTimeLeft.setText("");
    }
}

