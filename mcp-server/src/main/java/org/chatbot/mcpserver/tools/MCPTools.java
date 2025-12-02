package org.chatbot.mcpserver.tools;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MCPTools {

    @McpTool (name = "getEmployee", description = "get Information about an embployee")
    public Employee getEmployee(@McpToolParam (description = "the employee's name") String name) {
        return new Employee(name,4400,3);
    }

   @McpTool(name = "getAllEmployee",description = "return the list of all employees ")
    public List<Employee> getAllEmployees() {
        return  List.of(
                new Employee("salma",44000,5),
                new Employee("salma",44000,5),
                new Employee("salma",44000,5)

        );
    }
}
record Employee(String name,double salary,int seniority) {}

