import state.BotState;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * В ЭТОМ ФАЙЛИКЕ ОНО РУГАЕТСЯ НА ТО ЧТО МЕТОДЫ НЕ ВСЕГДА ЧТО-ТО ВОЗВРАЩВЮТ
 * Хотя это не так.
 * Посмотри, что можно сделать, чтобы не ругалось :)
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

/**
 * Класс обработки запросов пользователя и вызова соответсвующей логики
 */
public class RequestHandler {
    /** Поле состояние диалога бота */
    private final BotState state = BotState.Main;

    /**
     * Функция вызова логики по запросу
     * @param request - запрос
     * @return сообщение - ответ на запрос
     */
    public String handleRequest(Request request){
        switch (state){
            case Main -> executeMainCommand(request);
            case RaceInfo -> executeRaceInfoCommand(request);
        }
        state.nextState(request.command());
    }

    /**
     * Функция вызова по запросу команды, доступной из состояния диалога Main
     * @param request - запрос
     * @return - сообщение - резульат выполнение команды
     */
    private String executeMainCommand(Request request) {
        switch (request.command()) {
            case "/help" -> HelpCommand.getHelp();
            case "/list" -> ListCommand.getRaceList();
            case "/info" -> RaceCommand.getRaceInfo(request.argument());
        };
    }

    /**
     * Функция вызова по запросу команды, доступной из состояния диалога RaceInfo
     * @param request - запрос
     * @return - сообщение - резульат выполнение команды
     */
    private String executeRaceInfoCommand(Request request) {
        switch (request.command()) {
            case "/help" -> HelpCommand.getHelp();
            case "/more" -> RaceCommand.getMoreRaceInfo();
        };
    }
}
