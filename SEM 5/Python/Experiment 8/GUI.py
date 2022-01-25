import tkinter as tk
from tkinter import Label, IntVar, Radiobutton, StringVar, OptionMenu, Button, Checkbutton, Text
import tkinter.messagebox

def onClick():
    tkinter.messagebox.showinfo("Alert",  "Form submitted successfully!")

root = tk.Tk()
root.geometry("500x500")
root.configure(bg="black")
root.title('Registration form')

registrationFormTitle = Label(
    root,
    text="Registration form",
    width=20,
    font=("bold", 20),
    bg="#0066CC",
).place(anchor=tk.CENTER, relx=0.5, rely=0.1)

nameLabel = Label(
    root,
    text="Name:",
    width=20,
    font=("bold", 10),
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.3, rely=0.2)

nameEntry = Text(
    root,
    height=1.45,
    width=25,
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.7, rely=0.2)

emailLabel = Label(
    root,
    text="Email",
    width=20,
    font=("bold", 10),
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.3, rely=0.3)

emailEntry = Text(
    root,
    height=1.45,
    width=25,
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.7, rely=0.3)

genderLabel = Label(
    root,
    text="Gender",
    width=20,
    font=("bold", 10),
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.3, rely=0.4)

genderVar = IntVar()
maleButton = Radiobutton(
    root,
    text="Male",
    padx=9,
    variable=genderVar,
    value=1,
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.6, rely=0.4)

femaleButton = Radiobutton(
    root,
    text="Female",
    padx=9,
    variable=genderVar,
    value=2,
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.8, rely=0.4)

countryLabel = Label(
    root,
    text="Nationality",
    width=20,
    font=("bold", 10),
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.3, rely=0.5)

listOfCountries = ['India', 'US', 'UK', 'Germany', 'Austria',
                   'Switzerland', 'Argentina', 'Egypt', 'Indonesia', 'Turkey', 'New Zealand']
c = StringVar()
dropList = OptionMenu(root, c, *listOfCountries)
dropList.config(width=20, bg="#0066CC")
c.set('Select your nationality')
dropList.place(anchor=tk.CENTER, relx=0.7, rely=0.5)

languageLabel = Label(
    root,
    text="Language",
    width=20,
    font=('bold', 10),
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.3, rely=0.6)

englishVar = IntVar()
englishButton = Checkbutton(
    root,
    text="English",
    variable=englishVar,
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.6, rely=0.6)

germanVar = IntVar()
germanButton = Checkbutton(
    root,
    text="German",
    variable=germanVar,
    bg="#0066CC"
).place(anchor=tk.CENTER, relx=0.8, rely=0.6)

submitButton = Button(
    root,
    text='Submit',
    width=20,
    bg="#0066CC",
    command=onClick
).place(anchor=tk.CENTER, relx=0.5, rely=0.7)
root.mainloop()
