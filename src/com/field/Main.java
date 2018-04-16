package com.field;
import com.graphic.lwjglGraphics.LwjglGraphicsModule;
import com.field.Constants;
import static com.field.Constants.*;
import com.graphic.GraphicsModule;
import com.keyboard.KeyboardHandleModule;
import com.keyboard.lwjglmodule.LwjglKeyboardHandleModule;

public class Main {


    private static boolean endOfGame;

    /** Графический модуль игры*/
    private static GraphicsModule graphicsModule;

    /** "Клавиатурный" модуль игры, т.е. модуль для чтения запросов с клавиатуры*/
    private static KeyboardHandleModule keyboardModule;

    /** Игровое поле. См. документацию GameField */
    private static GameField gameField;

    /** Направление для сдвига, полученное за последнюю итерацию */
    private static ShiftDirection shiftDirection;

    /** Был ли за последнюю итерацию запрошен поворот фигуры */
    private static boolean isRotateRequested;

    /** Было ли за последнюю итерацию запрошено ускорение падения*/
    private static boolean isBoostRequested;

    /** Номер игровой итерации по модулю FRAMES_PER_MOVE.
     *  Падение фигуры вниз происходит если loopNumber % FRAMES_PER_MOVE == 0
     *  Т.е. 1 раз за FRAMES_PER_MOVE итераций.
     */
    private static int loopNumber;

    public static void main(String[] args) {

        LwjglGraphicsModule graphicsModule =new LwjglGraphicsModule();
        initFields();


        while(!endOfGame){
            input();
            logic();

            graphicsModule.draw(gameField);
            graphicsModule.sync(FPS);
        }

        graphicsModule.destroy();
    }


    private static void initFields() {
        loopNumber = 0;
        endOfGame = false;
        shiftDirection = ShiftDirection.AWAITING;
        isRotateRequested = false;
        graphicsModule = new LwjglGraphicsModule();
        keyboardModule = new LwjglKeyboardHandleModule();
        gameField = new GameField();
    }

    private static void input(){
        /// Обновляем данные модуля ввода
        keyboardModule.update();

        /// Считываем из модуля ввода направление для сдвига падающей фигурки
        shiftDirection = keyboardModule.getShiftDirection();

        /// Считываем из модуля ввода, хочет ли пользователь повернуть фигурку
        isRotateRequested = keyboardModule.wasRotateRequested();

        /// Считываем из модуля ввода, хочет ли пользователь "уронить" фигурку вниз
        isBoostRequested = keyboardModule.wasBoostRequested();

        /// Если был нажат ESC или "крестик" окна, завершаем игру
        endOfGame = endOfGame || keyboardModule.wasEscPressed() || graphicsModule.isCloseRequested();
}

