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

    private static final int FRAME_COLS = 8;

    private SpriteBatch batch;
    private FitViewport viewport;

    private Animation<TextureRegion> walkAnimation;
    private Texture walkTexture;
    private float stateTime;


    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        walkTexture = new Texture(Gdx.files.internal("walking.png"));
        TextureRegion[][] wakeupFrames = TextureRegion.split(walkTexture,
            walkTexture.getWidth() / FRAME_COLS,
            walkTexture.getHeight()
            );

        walkAnimation = new Animation<TextureRegion>(0.1f, wakeupFrames[0]);

        stateTime = 0f;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        batch.begin();

        batch.draw(currentFrame, 400, 250f);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        walkTexture.dispose();
    }
}
