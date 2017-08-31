package jetbrains.hub.tools.db.console

import javax.script.*


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
            // https://youtrack.jetbrains.com/v2/issue/KT-17921
            ex.printStackTrace()
            return true
        }

        if (result != null) {
            engine.put("res$i", result)
            println("res$i: ${result.javaClass.kotlin.simpleName}@${Integer.toHexString(result.hashCode())} = $result")
        } else {
            println("Unit")
        }

        return true
    }
}

