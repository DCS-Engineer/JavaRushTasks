package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path>
{
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();
    private boolean isSetPartOfName = false;
    private boolean isSetPartOfContent = false;
    private boolean isSetMinSize = false;
    private boolean isSetMaxSize = false;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        String str = new String(content);
        // 1111
        if (isSetPartOfName & isSetPartOfContent & isSetMinSize & isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName) & str.contains(partOfContent) & Files.size(file) > minSize & Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        // 1110
        if (isSetPartOfName & isSetPartOfContent & isSetMinSize & !isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName) & str.contains(partOfContent) & Files.size(file) > minSize)
            {
                foundFiles.add(file);
            }
        }
        // 1101
        if (isSetPartOfName & isSetPartOfContent & !isSetMinSize & isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName) & str.contains(partOfContent) & Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        // 1100
        if (isSetPartOfName & isSetPartOfContent & !isSetMinSize & !isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName) & str.contains(partOfContent))
            {
                foundFiles.add(file);
            }
        }
        // 1011
        if (isSetPartOfName & !isSetPartOfContent & isSetMinSize & isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName) & Files.size(file) > minSize & Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        // 1010
        if (isSetPartOfName & !isSetPartOfContent & isSetMinSize & !isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName) & Files.size(file) > minSize)
            {
                foundFiles.add(file);
            }
        }
        // 1001
        if (isSetPartOfName & !isSetPartOfContent & !isSetMinSize & isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName) & Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        // 1000
        if (isSetPartOfName & !isSetPartOfContent & !isSetMinSize & !isSetMaxSize)
        {
            if (file.getFileName().toString().contains(partOfName))
            {
                foundFiles.add(file);
            }
        }
        // 0111
        if (!isSetPartOfName & isSetPartOfContent & isSetMinSize & isSetMaxSize)
        {
            if (str.contains(partOfContent) & Files.size(file) > minSize & Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        // 0110
        if (!isSetPartOfName & isSetPartOfContent & isSetMinSize & !isSetMaxSize)
        {
            if (str.contains(partOfContent) & Files.size(file) > minSize)
            {
                foundFiles.add(file);
            }
        }
        // 0101
        if (!isSetPartOfName & isSetPartOfContent & !isSetMinSize & isSetMaxSize)
        {
            if (str.contains(partOfContent) & Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        // 0100
        if (!isSetPartOfName & isSetPartOfContent & !isSetMinSize & !isSetMaxSize)
        {
            if (str.contains(partOfContent))
            {
                foundFiles.add(file);
            }
        }
        // 0011
        if (!isSetPartOfName & !isSetPartOfContent & isSetMinSize & isSetMaxSize)
        {
            if (Files.size(file) > minSize & Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        // 0010
        if (!isSetPartOfName & !isSetPartOfContent & isSetMinSize & !isSetMaxSize)
        {
            if (Files.size(file) > minSize)
            {
                foundFiles.add(file);
            }
        }
        // 0001
        if (!isSetPartOfName & !isSetPartOfContent & !isSetMinSize & isSetMaxSize)
        {
            if (Files.size(file) < maxSize)
            {
                foundFiles.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    // Setters
    public void setPartOfName(String partOfName)
    {
        this.partOfName = partOfName;
        this.isSetPartOfName = true;
    }

    public void setPartOfContent(String partOfContent)
    {
        this.partOfContent = partOfContent;
        this.isSetPartOfContent = true;
    }

    public void setMinSize(int minSize)
    {
        this.minSize = minSize;
        this.isSetMinSize = true;
    }

    public void setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
        this.isSetMaxSize = true;
    }

    // Getters
    public List<Path> getFoundFiles()
    {
        return foundFiles;
    }
}
