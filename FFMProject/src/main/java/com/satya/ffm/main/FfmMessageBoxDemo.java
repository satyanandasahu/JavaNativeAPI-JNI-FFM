package com.satya.ffm.main;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class FfmMessageBoxDemo {

	public static void main(String[] args) throws Throwable { 
		// 1. Create a confined arena to manage off-heap memory safely and automatically
		try (Arena arena = Arena.ofConfined()) {

			// 2. Load user32.dll directly from the Windows system directory
			SymbolLookup user32 = SymbolLookup.libraryLookup("user32.dll", arena);
			MemorySegment messageBoxAddr = user32.find("MessageBoxA").orElseThrow();

			// 3. Describe the C function signature of MessageBoxA:
			// int MessageBoxA(HWND hWnd, LPCSTR lpText, LPCSTR lpCaption, UINT uType);
			FunctionDescriptor desc = FunctionDescriptor.of(ValueLayout.JAVA_INT, // Return type: int
					ValueLayout.ADDRESS, // Parameter 1: HWND hWnd (pointer)
					ValueLayout.ADDRESS, // Parameter 2: LPCSTR lpText (string pointer)
					ValueLayout.ADDRESS, // Parameter 3: LPCSTR lpCaption (string pointer)
					ValueLayout.JAVA_INT // Parameter 4: UINT uType (button flags)
			);

			// 4. Create a MethodHandle using the native system linker
			Linker linker = Linker.nativeLinker();
			MethodHandle messageBox = linker.downcallHandle(messageBoxAddr, desc);

			// 5. Allocate UTF-8 strings off-heap so the Windows OS C-runtime can read them
			System.out.println("Triggering Windows popup via FFM...");
			MemorySegment text = arena.allocateFrom("Hello from a Windows OS API called via FFM!");
			MemorySegment caption = arena.allocateFrom("Windows Popup");

			// 6. Invoke the native Windows API function directly!
			int result = (int) messageBox.invokeExact(MemorySegment.NULL, // hWnd (no parent window)
					text, // message text pointer
					caption, // window title pointer
					0 // MB_OK button style
			);

			System.out.println("Message box closed with button code: " + result);
		}

	}
}
