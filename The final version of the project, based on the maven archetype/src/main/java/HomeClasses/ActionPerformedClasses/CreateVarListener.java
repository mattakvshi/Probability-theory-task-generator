package HomeClasses.ActionPerformedClasses;

import HomeClasses.ConfigurationClasses.PathWRC;
import HomeClasses.MyException.MyExceptionNotPositive;
import HomeClasses.TaskManager.Task;
import HomeClasses.docx.WordWriter;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateVarListener implements ActionListener {
    static PathWRC pathWRC = new PathWRC();
    private final File home = FileSystemView.getFileSystemView().getHomeDirectory();
    private final String defaultFilePath = home.getAbsolutePath() + "\\Варианты";
    private final Set<Task> taskSet;
    private static String userFilePath;
    private final JPanel mainPanel;
    private final JTextField textFieldCountVar;
    FontSizeListener fontSizeListener;
    FontFamilyListener fontFamilyListener;


    public CreateVarListener
    (
            ColorListener colorListener, JPanel mainPanel, JTextField textFieldCountVar,
            FontSizeListener fontSizeListener, FontFamilyListener fontFamilyListener
    )
    {
        this.taskSet = colorListener.getTaskSet();
        this.mainPanel = mainPanel;
        this.textFieldCountVar = textFieldCountVar;
        this.fontSizeListener = fontSizeListener;
        this.fontFamilyListener = fontFamilyListener;
    }

    static {
        try {
            userFilePath = pathWRC.readFromTxt();
            if(userFilePath.isEmpty()){
                userFilePath = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserFilePath() {
        return userFilePath;
    }

    public void setUserFilePath(String userFilePath) {
        CreateVarListener.userFilePath = userFilePath;
    }

    private String fixNumerical (int num, String[] array)
    {
        String result = null;
        if(num == 11) result = array[2];
        else if(("" + num).endsWith("1")) result = array[0];
        else if(num > 11 && num < 15) result = array[2];
        else if(num % 10 > 1 && num % 10 < 5) result = array[1];
        else result = array[2];
        return result;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        try{
            int countVar = Integer.parseInt(textFieldCountVar.getText());
            if(countVar <= 0){throw new MyExceptionNotPositive();}
            String answer;
            String[] varArray = new String[]{"вариант будет", "варианта будут", "вариантов будут"};
            String[] yourArray = new String[]{"Ваш", "Ваши", "Ваши"};
            answer = "Подготовка к созданию вариантов завершена! " + fixNumerical(countVar, yourArray) +
                    " " + countVar + " " + fixNumerical(countVar, varArray) + " находится в папке " +
                    Objects.requireNonNullElse(userFilePath, "'Варианты' на рабочем столе.") +
                    "\nНабор номеров в варианте: ";
            String remark = "\n\nТак же при желании вы можете сменить путь сохранения сгенерированных вариантов во вкладке 'Настройки'.";
            String sobr = "\n\nПрочитав уведомление закройте окно и немного подождите. После завершения создания папка с вариантами сама откроется.";
            /**
             * Пацаны вы просто зацените эту строку, это вообще кайф. Она столько когда заменила.
             * У меня ещё было пару вариантов немного другой реализации, но в итоге я выбрал эту,
             * она и по времени сбалансированная и лаконичная, и читабельная.
             * @see Task
             * @see <a href="https://javarush.com/groups/posts/2203-stream-api">Вот документация по StreamAPI.</a>
             *
             * @author mattakvshi
             */

            String stringNumbersOfSet = taskSet.stream()
                    .sorted(Task.numberComparator)
                    .map(Task::toString)
                    .collect(Collectors
                            .joining(",")
                    );
            if(taskSet.size() == 0)
                JOptionPane.showMessageDialog(mainPanel, "Выберете хотя бы один номер!");
            else {
                JOptionPane.showMessageDialog(mainPanel, answer + stringNumbersOfSet + "." + remark + sobr );

                /**
                 * Пример вызова класса с back-end кода.
                 * @see Task
                 * @see <a href="https://javarush.com/groups/posts/2203-stream-api">Вот документация по StreamAPI.</a>
                 *
                 * @author Dmitriy + mattakvshi
                 */

                int [] taskArray = taskSet.stream()
                        .sorted(Task.numberComparator)
                        .mapToInt(Task::getNumberTask)
                        .toArray();

                /**
                 * Пример вызова класса с back-end кода.
                 * @see WordWriter
                 *
                 * @author Alex + mattakvshi
                 */
                WordWriter wordWriter = new WordWriter
                (
                        taskArray,
                        countVar,
                        Objects.requireNonNullElse(userFilePath, defaultFilePath),
                        Integer.parseInt(fontSizeListener.getFontSize()),
                        fontFamilyListener.getFontFamily(),
                        true
                );
                Desktop.getDesktop().open(new File(Objects.requireNonNullElse(userFilePath, defaultFilePath)));
            }
        }
        catch (Exception ex){
            if(ex instanceof MyExceptionNotPositive){
                System.out.println(ex.getMessage());
                String answer = "Введите корректное количество вариантов!\n" +
                        "Количество вариантов должно быть положительным числом!";
                JOptionPane.showMessageDialog(mainPanel,answer);
                textFieldCountVar.setText("");
            }
            else if(ex instanceof  NumberFormatException){
                System.out.println(ex.getMessage());
                String answer = "Введите корректное количество вариантов!\n" +
                        "Вы серьёзно? Колличество должно быть задано целым числом!";
                JOptionPane.showMessageDialog(mainPanel,answer);
                textFieldCountVar.setText("");
            }
            else if(ex instanceof NullPointerException){
                System.out.println(ex.getMessage());
                String answer = "Упс! Что-то пошло не так..\n" +
                        "Попробуйте заного выбрать нужные вам настройки." +
                        "Так же, если вы указывали путь, попробуйте его изменить.";
                JOptionPane.showMessageDialog(mainPanel,answer);
                textFieldCountVar.setText("");
            }
            else {
                System.out.println(ex.getMessage());
                String answer = "Если это сообщение вылезло, всё плохо...\n" +
                        " зовите команду разработчиков!";
                JOptionPane.showMessageDialog(mainPanel, answer);
                textFieldCountVar.setText("");
            }
        }
    }
}