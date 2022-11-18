package botLogic.state;

import request.Request;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Перечисление, хранящее состояние диалога бота
 */
public enum BotState {
    /**
     * Основное состояние, в котором бот выполняет базовый набор команд
     */
    Main {
        @Override
        public BotState nextState(Request request) {
            if (request.command().equals("/race"))
                return RaceInfo;
            return this;
        }

        @Override
        public ArrayList<String> getAvailableCommands() {
            String[] commands = new String[]{
                    "/start",
                    "/help",
                    "/list",
                    "/race"
            };
            return new ArrayList<>(Arrays.asList(commands));
        }
    },

    /**
     * Состояние в ветке диалога команды вывода информации о расе
     */
    RaceInfo {
        @Override
        public ArrayList<String> getAvailableCommands() {
            String[] commands = new String[]{
                    "/help",
                    "/info",
                    "/exit"
            };
            return new ArrayList<>(Arrays.asList(commands));
        }

        @Override
        public BotState nextState(Request request) {
            if (request.command().equals("/exit"))
                return Main;
            return this;
        }
    };

    /**
     * Функция перехода в следующее состояние
     *
     * @param request - команда, после которой состояние может измениться
     * @return возвращает новое состояние диалога бота
     */
    public abstract BotState nextState(Request request);

    /**
     * Функция получения доступных команд в текущем состоянии
     *
     * @return возвращает список доступных команд
     */
    public abstract ArrayList<String> getAvailableCommands();
}
