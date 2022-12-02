package command;

import messagePackage.Format;
import messagePackage.FormattedText;
import messagePackage.MessagePackage;
import messagePackage.MessagePackageBuilder;

/**
 * Класс команды выхода из текущей ветки диалога бота
 */
public class ExitCommand implements Command {
    @Override
    public MessagePackage getResult() {
        FormattedText text = new FormattedText("Что хочешь ещё узнать? :)", Format.NORMAL);
        return new MessagePackageBuilder()
                .addInformation(text)
                .build();
    }
}
