# -Google-Places-SDK-for-Android-AutoComplete

The Google Places SDK for Android allows you to include an autocomplete widget in your app that provides place predictions as the user types. This can be useful for search functionality or for collecting location information from the user.

Here is an example of how to use the autocomplete widget in an Android app:

    Add the Google Places SDK for Android as a dependency in your app's build.gradle file:
```
implementation 'com.google.android.libraries.places:places:2.3.0'
```
    Add the Google Places API key to your app's AndroidManifest.xml file:
```
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY"/>
```
    Add the AutocompleteSupportFragment to your layout file:
```
<fragment
    android:id="@+id/autocomplete_fragment"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:name="com.google.android.libraries.places.widget.AutocompleteSupportFragment"/>
```
    In your activity's onCreate() method, initialize the AutocompleteSupportFragment and set the place fields that you want to be returned:
```
AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
        getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
    @Override
    public void onPlaceSelected(Place place) {
        // Place selected
    }

    @Override
    public void onError(Status status) {
        // An error occurred
    }
});
```
That's it! The autocomplete widget will now display place predictions as the user types.

I hope this helps give you an idea of how to use the Google Places SDK for Android's autocomplete widget in your app. Let me know if you have any questions.
