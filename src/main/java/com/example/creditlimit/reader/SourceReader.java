package com.example.creditlimit.reader;

import com.example.creditlimit.model.PersonInfo;

import java.io.IOException;
import java.util.List;

public interface SourceReader {

    List<PersonInfo> getRecords() throws IOException;
}
