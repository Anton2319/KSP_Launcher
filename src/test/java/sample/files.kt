package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import javafx.stage.Stage

class files {
    @Test
    fun `Run`() {
//        print("RUNNING TESTS \n")
//        val rootDir = System.getProperty("user.dir")
//        val versionList = File("versionList.txt")
//        versionList.delete()
//        val FileOperations = FileOperations()
//        assertEquals(false, FileOperations.checkFiles(rootDir))
//        print("Successful!")
        val gui = gui()
        gui.Run();

    }
}