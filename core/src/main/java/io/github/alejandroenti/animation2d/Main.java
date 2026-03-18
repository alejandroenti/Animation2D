package io.github.alejandroenti.animation2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private static final int FRAME_COLS = 16;

    private SpriteBatch batch;
    private FitViewport viewport;

    private Animation<TextureRegion> wakeupAnimation;
    private Texture wakeupTexture;
    private float stateTime;


    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        wakeupTexture = new Texture(Gdx.files.internal("Bat-WakeUp.png"));
        TextureRegion[][] wakeupFrames = TextureRegion.split(wakeupTexture,
            wakeupTexture.getWidth() / FRAME_COLS,
            wakeupTexture.getHeight()
            );

        wakeupAnimation = new Animation<TextureRegion>(0.025f, wakeupFrames[0]);

        stateTime = 0f;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = wakeupAnimation.getKeyFrame(stateTime, true);

        batch.begin();

        batch.draw(currentFrame, 400, 250f);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        wakeupTexture.dispose();
    }
}
