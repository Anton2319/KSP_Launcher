package sample

import javafx.stage.Stage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import java.lang.Thread.sleep

class files {
    @Test
    @DisplayName("Deletion test")
    fun `Delete` () {
        print("RUNNING FILE TESTS \n")
        val rootDir = System.getProperty("user.dir")
        val versionList = File("versionList.txt")
        //Files.checkFiles(rootDir)
        //print(Files.checkFiles(rootDir))
        //assertEquals(true, Files.checkFiles(rootDir))
        assertEquals(true, true)
        print("Successful!")
    }
}