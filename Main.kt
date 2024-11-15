import javax.swing.*
import javax.swing.border.LineBorder
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

fun main() {
    Ui()
}

class Ui : JFrame(), ActionListener {
    private val firstNumField = makeTextField()
    private val opField = makeTextField()
    private val secondNumField = makeTextField()
    private val resultText = JLabel("Result: ")

    init {
        // setup basics for window
        title = "Sunny's Calculator"
        setSize(600, 500)
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = GridBagLayout()
        contentPane.background = Color(0x1F2023)

        // add panel for input fields
        val panel = JPanel()
        panel.layout = GridBagLayout()
        panel.background = Color(0x1F2023)

        // add layout and font styles
        val layoutSetup = GridBagConstraints().apply {
            fill = GridBagConstraints.HORIZONTAL
            insets = Insets(10, 10, 10, 10)
        }
        val fontStyle = Font("Montserrat", Font.BOLD, 16)

        // add label and field for first number
        layoutSetup.gridx = 0
        layoutSetup.gridy = 0
        panel.add(JLabel("First Number:").apply {
            foreground = Color(0xC1C9E1)
            font = fontStyle
        }, layoutSetup)
        layoutSetup.gridx = 1
        panel.add(firstNumField, layoutSetup)

        // add label and field for operator
        layoutSetup.gridx = 0
        layoutSetup.gridy = 1
        panel.add(JLabel("Operator:").apply {
            foreground = Color(0xC1C9E1)
            font = fontStyle
        }, layoutSetup)
        layoutSetup.gridx = 1
        panel.add(opField, layoutSetup)

        // add label and field for second number
        layoutSetup.gridx = 0
        layoutSetup.gridy = 2
        panel.add(JLabel("Second Number:").apply {
            foreground = Color(0xC1C9E1)
            font = fontStyle
        }, layoutSetup)
        layoutSetup.gridx = 1
        panel.add(secondNumField, layoutSetup)

        // add calculate button
        val calcButton = JButton("Calculate").apply {
            font = fontStyle
            background = Color(0x6C5DD3)
            foreground = Color(0xC1C9E1)
            isOpaque = true
            border = LineBorder(Color(0x333745), 2)
            addActionListener(this@Ui)
        }
        layoutSetup.gridx = 0
        layoutSetup.gridy = 3
        layoutSetup.gridwidth = 2
        panel.add(calcButton, layoutSetup)

        // add result label
        resultText.apply {
            foreground = Color(0xC1C9E1)
            font = fontStyle
        }
        layoutSetup.gridx = 0
        layoutSetup.gridy = 4
        layoutSetup.gridwidth = 2
        panel.add(resultText, layoutSetup)

        // add panel to window
        add(panel)
        isVisible = true
        setLocationRelativeTo(null)
    }

    // create a text field
    private fun makeTextField(): JTextField {
        return JTextField(10).apply {
            background = Color(0x24262C)
            foreground = Color(0xC1C9E1)
            font = Font("Montserrat", Font.BOLD, 16)
            border = LineBorder(Color(0x333745), 2)

            // change border color when the field is in focus
            addFocusListener(object : java.awt.event.FocusListener {
                override fun focusGained(e: java.awt.event.FocusEvent?) {
                    border = LineBorder(Color(0x6C5DD3), 2)
                }

                override fun focusLost(e: java.awt.event.FocusEvent?) {
                    border = LineBorder(Color(0x333745), 2)
                }
            })
        }
    }

    // calculate result when button is clicked
    override fun actionPerformed(e: ActionEvent) {
        try {
            val firstNum = firstNumField.text.toDouble()
            val operator = opField.text.trim()
            val secondNum = secondNumField.text.toDouble()

            // determine the result based on operator
            val result = when (operator) {
                "+" -> firstNum + secondNum
                "-" -> firstNum - secondNum
                "*" -> firstNum * secondNum
                "/" -> firstNum / secondNum
                else -> {
                    resultText.text = "Invalid operator"
                    return
                }
            }

            resultText.text = "Result: $result"
        } catch (ex: Exception) {
            resultText.text = "Error: ${ex.message}"
        }
    }
}
