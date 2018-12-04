package src.view;

import src.controller.Controller;
import src.objects.PeriodOfStudy;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RepeatDatesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;

    private PeriodOfStudy currentPOS;

    public RepeatDatesDialog(PeriodOfStudy currentPOS) {
        this.currentPOS = currentPOS;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        startDateSpinner.setModel(new SpinnerDateModel(Calendar.getInstance().getTime(),
                null, null, Calendar.DAY_OF_WEEK));
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));

        endDateSpinner.setModel(new SpinnerDateModel(Calendar.getInstance().getTime(),
                null, null, Calendar.DAY_OF_WEEK));
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd"));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //Create the new POS
        char newLabel = currentPOS.getLabel().charAt(0);
        newLabel++;

        String startDate = dateFormat.format(startDateSpinner.getValue());
        String endDate = dateFormat.format(endDateSpinner.getValue());

        Controller.addPeriodOfStudy(Integer.valueOf(currentPOS.getLoginID()),
                String.valueOf(newLabel), startDate, endDate,
                currentPOS.getLevelOfStudy());

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
