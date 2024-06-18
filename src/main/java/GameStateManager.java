public class GameStateManager {

    private Game game;
    private GameState currentState;

    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int SETTING = 2;
    public static final int LEVEL = 3;
    public static final int STORY = 4;
    private MusicPlayer musicPlayer = new MusicPlayer();

    public GameStateManager(Game game) {
        this.game = game;
        currentState = new MainMenuState(this, game);
        currentState.init();
        game.changeState(currentState);
    }

    public void setState(int state) {
        setState(state, 0, 0);
    }
    public void setState(int state, int chapter) {
        setState(state, chapter, 0);
    }

    public void setState(int state, int chapter, int level) {
        if (currentState != null) {
            currentState.cleanup();
        }

        musicPlayer.stop();
        switch (state) {
            case MENU:
                currentState = new MainMenuState(this, game);
                break;
            case PLAY:
                currentState = new PlayState(this, game);
                break;
            case SETTING:
                currentState = new SettingsMenuState(this, game);
                break;
            case LEVEL:
                currentState = new LevelPanel(this, game, chapter, level);
                break;
            case STORY:
                String musicFile = "assets/sound/story.mp3";
                musicPlayer.play(musicFile, true);
                currentState = new StoryState(this, chapter);
                break;
            default:
                currentState = new MainMenuState(this, game);
                break;
        }

        currentState.init();
        game.changeState(currentState);
    }

    public void setNextStateAfterStory() {
        setState(PLAY);
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public GameState getInitialState() {
        return new MainMenuState(this, game);
    }

    public void update() {
        currentState.update();
    }

    public void render() {
        currentState.render();
    }

    public void handleInput() {
        currentState.handleInput();
    }
}
