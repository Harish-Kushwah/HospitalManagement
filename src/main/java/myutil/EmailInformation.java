
package myutil;

/**
 *
 * @author haris
 */
public class EmailInformation {
    String from,to,subject,body,template;
    
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
    
}
