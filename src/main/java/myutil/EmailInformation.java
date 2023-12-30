
package myutil;

/**
 *
 * @author haris
 */
public class EmailInformation {
    String from,to,subject,body,template;
    String attatch_file_path;
    
    public void setAttachFilePath(String file_path)
    {
        this.attatch_file_path = file_path;
    }
    public void setSendFrom(String from)
    {
        this.from = from;
    }
    public void setSendTo(String to)
    {
        this.to = to;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    public void setBody(String body)
    {
        this.body = body;
    }
    public void setTemplate(String template)
    {
        this.template = template;
    }
    
    public String getSendFrom()
    {
        return this.from;
    }
    public String getSendTo()
    {
        return this.to;
    }
    public String getSubject()
    {
        return this.subject;
    }
    public String getBody()
    {
        return this.body;
    }
    public String getTemplate()
    {
        return this.template;
    }
    
     public String getAttachFilePath()
    {
        return this.attatch_file_path;
    }
    
}
