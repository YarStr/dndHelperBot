package state;

/**
 * Перечисление, хранящее состояние диалога бота
 */
public enum BotState {
    /**
     * Основное состояние, в котором бот выполняет базовый набор команд
     */
    Main {
        @Override
        public BotState nextState(String command){
            if (command.equals("/info"))
                return RaceInfo;
            return this;
        }
    },

    /**
     * Состояние в ветке диалога команды вывода информации о расе
     */
    RaceInfo {
        @Override
        public BotState nextState(String command){
            return Main;
        }
    };

    /**
     * Функция перехода в следующее состояние
     * @param command - команда, после которой состояние может измениться
     * @return возвращает новое состояние диалога бота
     */
    public abstract BotState nextState(String command);
}
