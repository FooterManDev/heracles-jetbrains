package io.github.heraclessupport

import com.intellij.ide.FileIconProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class HeraclesLangProvider : FileIconProvider {
    override fun getIcon(file: VirtualFile, flags: Int, project: Project?): Icon? {
        if(file.path.contains("/heracles/quests/")  && file.extension.equals("json")) {
            return Icons.questFile
        }
        return null
    }
}