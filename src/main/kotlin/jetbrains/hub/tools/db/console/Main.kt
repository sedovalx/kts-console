package jetbrains.hub.tools.db.console

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager


fun main(args: Array<String>) {
    HubConsole().run()
}

class HubConsole {
    fun run() {
        startREPL()
    }

    private fun startREPL() {
        val engine = ScriptEngineManager().getEngineByExtension("kts")!!

        var i = 0
        while (cycleREPL(engine, i++)) { }
    }

    private fun cycleREPL(engine: ScriptEngine, i: Int): Boolean {
        print("> ")
        val input = readLine() ?: "exit"
        if (input == "exit") {
            return false
        }

        val result = try {
            engine.eval(input)
        } catch (ex: Exception) {
            println(ex)
            return true
        }

        if (result !is Unit) {
            engine.put("res$i", result)
            println("res$i: ${result.javaClass.name}@${Integer.toHexString(result.hashCode())} = $result")
        } else {
            println("Unit")
        }

        return true
    }
}

