import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class PasswordGeneratorDemo {

	private JFrame frmPasswordGenerator;
	private JTextField usernameTextField;
	private JPasswordField passwordPasswordField;
	private JTextField purposeTextField;
	private JTextField generatedPasswordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordGeneratorDemo window = new PasswordGeneratorDemo();
					window.frmPasswordGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PasswordGeneratorDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordGenerator = new JFrame();
		frmPasswordGenerator.setResizable(false);
		frmPasswordGenerator.setTitle("Password Generator Demo");
		frmPasswordGenerator.setBounds(100, 100, 894, 356);
		frmPasswordGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordGenerator.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel label = new JLabel("Username:");
		label.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(label, "2, 2");
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		usernameTextField.setColumns(10);
		frmPasswordGenerator.getContentPane().add(usernameTextField, "4, 2, 5, 1, fill, default");
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(label_1, "2, 4");
		
		passwordPasswordField = new JPasswordField();
		passwordPasswordField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(passwordPasswordField, "4, 4, 5, 1, fill, default");
		
		JLabel label_2 = new JLabel("Purpose:");
		label_2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(label_2, "2, 6");
		
		purposeTextField = new JTextField();
		purposeTextField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		purposeTextField.setColumns(10);
		frmPasswordGenerator.getContentPane().add(purposeTextField, "4, 6, 5, 1, fill, default");
		
		JLabel label_3 = new JLabel("Length:");
		label_3.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(label_3, "2, 8");
		
		JFormattedTextField lengthFormattedTextField = new JFormattedTextField();
		lengthFormattedTextField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(lengthFormattedTextField, "4, 8, fill, default");
		
		JLabel label_4 = new JLabel("Characters:");
		label_4.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(label_4, "2, 10");
		
		JCheckBox uppercaseCheckBox = new JCheckBox("Uppercase");
		uppercaseCheckBox.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(uppercaseCheckBox, "4, 10");
		
		JCheckBox digitCheckBox = new JCheckBox("Digits");
		digitCheckBox.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(digitCheckBox, "6, 10");
		
		JCheckBox lowercaseCheckBox = new JCheckBox("Lowercase");
		lowercaseCheckBox.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(lowercaseCheckBox, "4, 12");
		
		JCheckBox symbolCheckBox = new JCheckBox("Digits");
		symbolCheckBox.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(symbolCheckBox, "6, 12");
		
		JButton generateButton = new JButton("Generate Password");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameTextField.getText();
                String password = passwordPasswordField.getText();
                String purpose = purposeTextField.getText();
                String lengthString = lengthFormattedTextField.getText();
                String generatedPassword = "";
                int length = Integer.parseInt(lengthString);
                String usernameShaValue = sha512(username);
                String passwordShaValue = sha512(password);
                String purposeShaValue = sha512(purpose);
                String key = sha512(usernameShaValue + passwordShaValue + purposeShaValue);
                String counterString = key.substring(0, 4);
                String temp;
                for (int counter = Integer.parseInt(counterString, 16), i = 0; i < counter; ++i) {
                    temp = usernameShaValue;
                    usernameShaValue = sha512(passwordShaValue);
                    passwordShaValue = sha512(purposeShaValue);
                    purposeShaValue = sha512(key);
                    key = sha512(temp);
                    key = sha512(usernameShaValue + passwordShaValue + purposeShaValue + key);
                }
                String[] uppercase = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Z", "X", "C", "V", "B", "N", "M", "A", "S", "D", "F", "G", "H", "J", "K", "L" };
                String[] lowercase = { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "z", "x", "c", "v", "b", "n", "m", "a", "s", "d", "f", "g", "h", "j", "k", "l" };
                String[] digit = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
                String[] symbol = { "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "-", "=", "[", "]", "{", "}", ";", ":", "<", ">", "?", ",", ".", "\\", "/" };
                ArrayList<String> charList = new ArrayList<String>();
                if (uppercaseCheckBox.isSelected()) {
                    charList.addAll(Arrays.asList(uppercase));
                }
                if (lowercaseCheckBox.isSelected()) {
                    charList.addAll(Arrays.asList(lowercase));
                }
                if (digitCheckBox.isSelected()) {
                    charList.addAll(Arrays.asList(digit));
                }
                if (symbolCheckBox.isSelected()) {
                    charList.addAll(Arrays.asList(symbol));
                }
                String[][] charTable = new String[16][16];
                int charAt = 0;
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 16; k++) {
                        charAt %= charList.size();
                        charTable[j][k] = charList.get(charAt);
                        ++charAt;
                    }
                }
                String[][] tempCharTable = new String[16][16];
                for (int counter = 0; counter < length; ++counter) {
                    for (int i = 0; i < 16; i++) {
                        for (int j = 0; j < 16; j++) {
                            tempCharTable[i][j] = charTable[(i + 2) % 16][(j + 14) % 16];
                        }
                    }
                    charTable = tempCharTable;
                    char columnIndexChar = key.charAt(counter * 2 % 64);
                    char rowIndexChar = key.charAt((counter * 2 + 1) % 64);
                    int columnIndexInt = Integer.parseInt(Character.toString(columnIndexChar), 16);
                    int rowIndexInt = Integer.parseInt(Character.toString(rowIndexChar), 16);
                    generatedPassword += charTable[columnIndexInt][rowIndexInt];
                }
                generatedPasswordTextField.setText(generatedPassword);
			}
		});
		generateButton.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(generateButton, "8, 12");
		
		JLabel label_5 = new JLabel("Your Password:");
		label_5.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		frmPasswordGenerator.getContentPane().add(label_5, "2, 14");
		
		generatedPasswordTextField = new JTextField();
		generatedPasswordTextField.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 30));
		generatedPasswordTextField.setColumns(10);
		frmPasswordGenerator.getContentPane().add(generatedPasswordTextField, "2, 16, 7, 1, fill, default");
	}
	
	public static String sha512(String input) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            final byte[] messageDigest = md.digest(input.getBytes());
            final BigInteger no = new BigInteger(1, messageDigest);
            String hashtext;
            for (hashtext = no.toString(16); hashtext.length() < 32; hashtext = "0" + hashtext) {}
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
