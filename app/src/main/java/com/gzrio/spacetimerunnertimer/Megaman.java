package com.gzrio.spacetimerunnertimer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class Megaman {
    private ImageView img;
    private Context context;

    private String currentState = "idle";

    private List<Drawable> idleFrameList = new ArrayList<>();
    private int idleFrameNo = 0;
    private int idleDelayPerFrame = 100;

    private List<Drawable> poseFrameList = new ArrayList<>();
    private int poseFrameNo = 0;
    private int poseDelayPerFrame = 80;

    private List<Drawable> runFrameList = new ArrayList<>();
    private int runFrameNo = 0;
    private int runDelayPerFrame = 80;

    public Megaman(ImageView img, Context context) {
        this.img = img;
        this.context = context;

        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_3));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_4));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_3));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_4));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_2));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_3));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_4));
        idleFrameList.add(this.context.getDrawable(R.drawable.frame_idle_3));

        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_1));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_2));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_3));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_4));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_5));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_6));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_5));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_4));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_5));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_6));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_5));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_4));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_5));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_6));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_5));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_4));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_3));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_2));
        poseFrameList.add(this.context.getDrawable(R.drawable.frame_pose_1));

        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_1));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_2));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_3));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_4));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_5));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_6));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_7));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_8));
        runFrameList.add(this.context.getDrawable(R.drawable.frame_run_9));
    }

    public Megaman Idle() {
        idleFrameNo = 0;
        currentState = "idle";
        AnimateIdle();

        return this;
    }

    public Megaman Pose(@Nullable Runnable nextRunnable) {
        poseFrameNo = 0;
        currentState = "pose";
        AnimatePose(nextRunnable);

        return this;
    }

    public Megaman Run() {
        runFrameNo = 0;
        currentState = "run";
        AnimateRun();

        return this;
    }

    private void AnimateIdle() {
        if(!currentState.equals("idle")) {
            return;
        }

        if(idleFrameNo > idleFrameList.size() - 1) {
            idleFrameNo = 0;
        }

        img.setImageDrawable(idleFrameList.get(idleFrameNo));
        idleFrameNo++;

        img.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimateIdle();
            }
        }, idleDelayPerFrame);
    }

    private void AnimatePose(@Nullable Runnable nextRunnable) {
        if(poseFrameNo > poseFrameList.size() - 1) {
            poseFrameNo = 0;

            if(nextRunnable != null) {
                nextRunnable.run();
            }

            return;
        }

        img.setImageDrawable(poseFrameList.get(poseFrameNo));
        poseFrameNo++;

        img.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatePose(nextRunnable);
            }
        }, poseDelayPerFrame);
    }

    private void AnimateRun() {
        if(!currentState.equals("run")) {
            return;
        }

        if(runFrameNo > runFrameList.size() - 1) {
            runFrameNo = 0;
        }

        img.setImageDrawable(runFrameList.get(runFrameNo));
        runFrameNo++;

        img.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimateRun();
            }
        }, runDelayPerFrame);
    }
}
