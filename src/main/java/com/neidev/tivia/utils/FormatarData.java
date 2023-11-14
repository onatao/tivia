package com.neidev.tivia.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatarData {
        public static String formatar() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return LocalDateTime.now().format(formatter);
        }

        private FormatarData() {
        }
    }


