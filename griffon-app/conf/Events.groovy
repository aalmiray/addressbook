import griffon.util.ApplicationHolder  
import griffon.core.GriffonApplication  
  
onUncaughtExceptionThrown = { x ->
    StringWriter sw = new StringWriter()
    x.printStackTrace(new PrintWriter(sw))
    ApplicationHolder.application.withMVCGroup('exception') { m, v, c ->  
        m.title = 'Error'  
        m.message = """ 
        Oops! An unexpected error occurred and we do not 
        know what to do with it. However instead of 
        crashing we thought you would like to know that 
        the problem was caused by  

        $sw 

        Also, look at the logging information printed in 
        your console.
        """.stripIndent(12)  
        c.show()  
    }  
}  
  