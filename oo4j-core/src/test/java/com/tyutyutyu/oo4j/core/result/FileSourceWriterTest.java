package com.tyutyutyu.oo4j.core.result;

import com.tyutyutyu.oo4j.core.generator.JavaTableTypeModel;
import com.tyutyutyu.oo4j.core.generator.JavaType;
import com.tyutyutyu.oo4j.core.generator.JavaTypeField;
import com.tyutyutyu.oo4j.core.javalang.JavaClass;
import com.tyutyutyu.oo4j.core.template.FreemarkerApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FileSourceWriterTest {

    private FileSourceWriter fileSourceWriter;

    @TempDir
    Path tempDir;

    @BeforeEach
    void beforeEach() {
        FreemarkerApi freemarkerApi = mock(FreemarkerApi.class);
        when(freemarkerApi.generate(any(), any())).thenReturn("X");
        fileSourceWriter = new FileSourceWriter(freemarkerApi, tempDir, true);
    }

    @Test
    void testWriteType() {

        // given
        JavaType javaType = new JavaType(
                "a.b.c",
                List.of(),
                "TestClass",
                "MY_SCHEMA",
                "MY_TYPE",
                List.of(
                        new JavaTypeField("p_param1", JavaClass.STRING, false)
                )
        );

        // when
        fileSourceWriter.writeType(javaType);

        // then
        assertThat(tempDir.resolve("a/b/c/TestClass.java")).isNotEmptyFile();
    }

    @Test
    void testWriteTableType() {

        // given
        JavaTableTypeModel javaTableTypeModel = new JavaTableTypeModel(
                "i.o.p",
                List.of(),
                "TableTypeClass",
                "",
                "",
                ""
        );

        // when
        fileSourceWriter.writeTableType(javaTableTypeModel);

        // then
        assertThat(tempDir.resolve("i/o/p/TableTypeClass.java")).isNotEmptyFile();
    }

    @Test
    void testWriteProcedure() {

        // given
        JavaProcedureMetadata javaProcedureMetadata = new JavaProcedureMetadata(
                "c.v.b",
                List.of(),
                "ProcedureClass",
                "",
                List.of(),
                List.of(),
                List.of(),
                List.of()
        );

        // when
        fileSourceWriter.writeProcedure(javaProcedureMetadata);

        // then
        assertThat(tempDir.resolve("c/v/b/ProcedureClass.java")).isNotEmptyFile();
    }

    @Test
    void testWriteSqlReturnTypeFactory() {

        // given
        String packageName = "r.t.z";

        // when
        fileSourceWriter.writeSqlReturnTypeFactory(packageName);

        // then
        assertThat(tempDir.resolve("r/t/z/SqlReturnTypeFactory.java")).isNotEmptyFile();
    }

    @Test
    void testWriteSqlTypeValueFactory() {

        // given
        String packageName = "g.h.j";

        // when
        fileSourceWriter.writeSqlTypeValueFactory(packageName);

        // then
        assertThat(tempDir.resolve("g/h/j/SqlTypeValueFactory.java")).isNotEmptyFile();
    }

}