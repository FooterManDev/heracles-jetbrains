package io.github.heraclessupport

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.jsonSchema.extension.JsonSchemaFileProvider
import com.jetbrains.jsonSchema.extension.JsonSchemaProviderFactory
import com.jetbrains.jsonSchema.extension.SchemaType
import org.jetbrains.annotations.Nls

class SchemaProvider : JsonSchemaProviderFactory {
    override fun getProviders(project: Project): List<JsonSchemaFileProvider> {
        return java.util.List.of<JsonSchemaFileProvider>(SchemaFileProvider(project))
    }

    internal inner class SchemaFileProvider(private val project: Project) : JsonSchemaFileProvider {
        override fun isAvailable(file: VirtualFile): Boolean {
            return file.name.endsWith(".json") && file.path.contains("/heracles/quests/")
        }

        override fun getName() = "Heracles"

        override fun getSchemaFile() = JsonSchemaProviderFactory.getResourceFile(SchemaProvider::class.java, "/schema.json")

        override fun getSchemaType() = SchemaType.embeddedSchema
    }
}
