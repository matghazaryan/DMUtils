package dmutils.com.dmutils.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * <dmutils.com.dmutils.view.GradientView
 * android:id="@+id/forgot_password_gradient_view"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent" />
 * <p>
 * private GradientView mGradientView;
 * <p>
 * final List<String> colorList = new ArrayList<>();
 * colorList.add(Gradient.START_COLOR);
 * colorList.add(Gradient.END_COLOR);
 * <p>
 * final List<Float> positionList = new ArrayList<>();
 * positionList.add(0.0f);
 * positionList.add(1.0f);
 * <p>
 * mGradientView.setGradientBackground(colorList, positionList);
 */

public final class DMUtilGradientView extends View {

    private Context context;
    private int[] colors = new int[]{};
    private float[] positions = new float[]{};

    public DMUtilGradientView(final Context context) {
        super(context);
        this.context = context;
    }

    public DMUtilGradientView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setGradientBackground(final List<String> colorList, final List<Float> positionList) {
        final int[] colors = new int[colorList.size()];
        final float[] positions = new float[positionList.size()];

        for (int i = 0; i < colorList.size(); i++) {
            String colorString = colorList.get(i);

            colors[i] = android.graphics.Color.parseColor(parseStringToColor(colorString));
        }

        for (int i = 0; i < positionList.size(); i++) {
            positions[i] = positionList.get(i);
        }

        addGradientBackground(colors, positions);
    }

    private void addGradientBackground(final int[] colors, final float[] positions) {
        this.colors = colors;
        this.positions = positions;

        setViewBackground(createLinearGradient(colors, positions));
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    private void setViewBackground(PaintDrawable linearGradient) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable(linearGradient);
        } else {
            this.setBackground(linearGradient);
        }
    }

    private PaintDrawable createLinearGradient(final int[] colors, final float[] positions) {
        final ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                final LinearGradient lg = new LinearGradient(0, 0, 0, height,
                        colors,
                        positions,
                        Shader.TileMode.REPEAT);
                return lg;
            }
        };
        final PaintDrawable p = new PaintDrawable();
        p.setShape(new RectShape());
        p.setShaderFactory(sf);
        return p;
    }

    public String parseStringToColor(final String color) {
        return "#" + color;
    }
}

