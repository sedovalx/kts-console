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
        val engineFactory = ScriptEngineManager().getEngineByExtension("kts")?.factory!!

        var i = 0
        val bindings = SimpleBindings()
        while (cycleREPL(engineFactory, bindings, i++)) { }
    }

    private fun cycleREPL(engineFactory: ScriptEngineFactory, bindings: SimpleBindings, i: Int): Boolean {
        val engine = engineFactory.scriptEngine
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE)
        print("> ")
        val input = readLine() ?: "exit"
        if (input == "exit") {
            return false
        }

        val result = try {
            engine.eval(input)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return true
        }

        if (result != null) {
            bindings.put("res$i", result)
            println("res$i: ${result.javaClass.name}@${Integer.toHexString(result.hashCode())} = $result")
        } else {
            println("Unit")
        }

        return true
    }
}

