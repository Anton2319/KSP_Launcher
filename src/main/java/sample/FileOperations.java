package sample;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;

import org.junit.Test;

import javafx.stage.Stage;
import net.lingala.zip4j.*;
import net.lingala.zip4j.exception.ZipException;

public class FileOperations {
    public boolean checkFiles(String root) {
        //Проверяем, есть ли папка versions
        File versionsDir = new File("versions");
        if (versionsDir.isDirectory()) {
        } else {
            versionsDir.mkdir();
        }

        //Провряем, есть ли файл versionList
        File versionListFile = new File("versionList.txt");
        File bgFile = new File("bg.png");
        if (versionListFile.exists() && bgFile.exists()) {
            return true;
        } else {
            downloadFiles(root);
            return false;
        }
    }

    public boolean downloadFiles(String root) {
        File versionListFile = new File("versionList.txt");
        File bgFile = new File("bg.png");
        GUIMessages message = new GUIMessages();
        //Если нет, пытаемся скачать
        try {
            message.showMessage("Подождите, пока нужные файлы загрузятся\nиз интернета");
            //Загружаем файлы в виде архива
            URL website = new URL("http://ksplauncher.000webhostapp.com/files.zip");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("files.zip");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            //Распаковываем
            try {
                ZipFile zipFile = new ZipFile("files.zip");
                zipFile.extractAll(root);
                return true;
            } catch (Exception e) {
                message.showError("Не удалось распаковать файл\nВы уверены, что у вас есть права работать с этой папкой?");
                e.printStackTrace();
            }
        } catch (Exception e) {
            //Если не получилось - создаём файл с сообщением о неудаче
            try {
                versionListFile.createNewFile();
            } catch (Exception ex) {
                message.showMessage("Не удалось создать файл\nВы уверены, что у вас есть права работать с этой папкой?");
                ex.printStackTrace();
            }
            try (FileWriter writer = new FileWriter("versionList.txt", false)) {
                String text = "СБОЙ СОЕДИНЕНИЯ!" ;
                String text1 = "Не удалось получить нужные файлы" ;
                String text2 = "Подключитесь к интернету и попробуйте снова" ;
                writer.write(text);
                writer.append('\n');
                writer.write(text1);
                writer.append('\n');
                writer.write(text2);
                message.showError(text + "\n" + text1 + "\n" + text2);
            } catch (Exception ex) {
                message.showError("Не удалось записать данные в файл\nВы уверены, что у вас есть права работать с этой папкой?");
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }
}
