package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.ProgramResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeExecuteService {

    public ProgramResult executeProgram(String code) {
        List<String> errorsWhileSave = saveFile(code);
        if(errorsWhileSave.isEmpty()) {
            ProgramResult result = executeProgram();
            result.setCode(code);
            return result;
        }
        else {
            return ProgramResult.builder()
                    .results(new ArrayList<>())
                    .code(code)
                    .errors(errorsWhileSave)
                    .build();
        }
    }

    private List<String> saveFile(String code) {
        File javaFile = new File("Main.java");
        List<String> errors = new ArrayList<>();

        if(!javaFile.exists()) {
            try {
                javaFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        try {
            PrintWriter pw = new PrintWriter(javaFile);
            pw.println(code);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }


        try {
            Process pro = Runtime.getRuntime().exec("javac Main.java");
            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
            String temp;
            while ((temp = br.readLine()) != null) {
                if(temp.contains("NOTE"))
                    continue;
                errors.add(temp);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return errors;
    }

    private ProgramResult executeProgram() {
        try {
            Process pro = Runtime.getRuntime().exec("java -cp . Main");

            BufferedReader resultReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
            List<String> results = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            String temp;
            while ((temp = resultReader.readLine()) != null) {
                results.add(temp);
            }
            while ((temp = errorReader.readLine()) != null) {
                if(temp.contains("NOTE"))
                    continue;
                errors.add(temp);
            }

            return ProgramResult.builder()
                    .results(results)
                    .errors(errors)
                    .build();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
