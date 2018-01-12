package laucher.com.mylauncher.activites;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import laucher.com.mylauncher.R;

import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;

public class Launcher extends Activity {
    private SlidingUpPanelLayout mLayout;
    private RelativeLayout layoutApps;
    private LinearLayout btnHandler;
    private String TAG = "Launcher";
    private FrameLayout fmMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        setContentView(R.layout.activity_main);
        initView();
        initAction();

    }

    private void initView() {
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_panel);
        layoutApps = (RelativeLayout) findViewById(R.id.all_apps);
        btnHandler = (LinearLayout) findViewById(R.id.btn_handler);
        fmMain = (FrameLayout) findViewById(R.id.fm_main);


    }

    private void initAction() {
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.e(TAG, "onPanelSlide, offset " + slideOffset);
                fmMain.setAlpha(1.0f - slideOffset);
                layoutApps.setAlpha(slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.e(TAG, "onPanelStateChanged, offset " + newState);
                // hien thi
                if (newState.equals(EXPANDED)) {
                    btnHandler.setVisibility(View.GONE);
                } else {
                    btnHandler.setVisibility(View.VISIBLE);

                }

            }

        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }

    }

    public static Launcher getLauncher(Context context) {
        if (context instanceof Launcher) {
            return (Launcher) context;
        }
        return ((Launcher) ((ContextWrapper) context).getBaseContext());
    }
}
